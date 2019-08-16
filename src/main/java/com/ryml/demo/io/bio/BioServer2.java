package com.ryml.demo.io.bio;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * description:多线程BIO
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/8/16
 */
public class BioServer2 {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9000);

        new Thread(()->{
            try {
                while(true){
                    Socket socket = serverSocket.accept();
                    //多线程BIO的基础上加上线程池就是伪异步IO
                    new Thread(()->{
                        try {
                            InputStream inputStream = socket.getInputStream();

                            byte[] bytes = new byte[1024];

                            while(true){
                                int read = inputStream.read(bytes);
                                if (read == -1){
                                    break;
                                }
                                System.out.println(new String(bytes,0,read));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
