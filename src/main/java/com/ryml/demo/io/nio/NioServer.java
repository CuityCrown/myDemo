package com.ryml.demo.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * description:NIO服务器端
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/8/16
 */
public class NioServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);

        ServerSocket serverSocket = serverSocketChannel.socket();

        serverSocket.bind(new InetSocketAddress(8091));

        Selector selector = Selector.open();

        //注册是否可建立连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while(selector.select() > 0){
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isAcceptable()){
                    System.out.println("连接已建立");
                    ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();

                    //空轮询
                    SocketChannel accept = server.accept();
                    if (accept == null){
                        continue;
                    }

                    accept.configureBlocking(false);
                    //注册是否可读或者可写事件
                    accept.register(selector,SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                }
                if (selectionKey.isReadable()){
                    System.out.println("监测到可读");
                    ByteBuffer readBuffer = ByteBuffer.allocateDirect(1024);
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                    readBuffer.clear();
                    socketChannel.read(readBuffer);
                    readBuffer.flip();
                    String receiveData= Charset.forName("UTF-8").decode(readBuffer).toString();
                    System.out.println("receiveData:"+receiveData);
                    // 把读到的数据绑定到key中
                    //selectionKey.attach("server message echo:"+receiveData);
                }

                // 实际上服务端不在意这个，这个写入应该是client端关心的，这只是个demo,顺便试一下selectionKey的attach方法
                if(selectionKey.isWritable()){
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();

                    ByteBuffer writeBuffer = ByteBuffer.allocateDirect(1024);

                    String message = (String) selectionKey.attachment();
                    if(message==null){
                        continue;
                    }
                    selectionKey.attach(null);

                    writeBuffer.clear();
                    writeBuffer.put(message.getBytes());
                    writeBuffer.flip();
                    while(writeBuffer.hasRemaining()){
                        socketChannel.write(writeBuffer);
                    }
                }
            }
        }
    }

}
