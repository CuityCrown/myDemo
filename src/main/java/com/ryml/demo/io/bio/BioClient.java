package com.ryml.demo.io.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/8/16
 */
public class BioClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",9000);

        while(true){
            OutputStream outputStream = socket.getOutputStream();

            outputStream.write((new Date()+"我是测试数据").getBytes());

            outputStream.flush();
        }

    }

}
