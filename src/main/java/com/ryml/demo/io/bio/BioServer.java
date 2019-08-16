package com.ryml.demo.io.bio;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * description:单线程BIO
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/8/16
 */
public class BioServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8000);

        while(true){
            Socket accept = serverSocket.accept();

            InputStream inputStream = accept.getInputStream();

            byte[] bytes = new byte[1024];

            while(true){
                int read = inputStream.read(bytes);
                if (read == -1){
                    break;
                }
                System.out.println(new String(bytes,0,read));
            }
        }

    }

}
