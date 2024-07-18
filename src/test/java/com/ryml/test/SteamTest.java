package com.ryml.test;

import java.io.*;

/**
 * @Description:
 * @author: 刘一博
 * @date: 2024/6/6 14:24
 */
public class SteamTest {

    public static void main(String[] args) throws IOException {
        File file = new File("E:\\new-airline-logo");
        File[] files = file.listFiles();
        for (File image : files) {
            String name = image.getName();
            String[] split = name.split("\\.");
            String newName = split[0].toUpperCase() + ".png";
            FileInputStream fileInputStream = new FileInputStream(image);
            byte[] bytes = new byte[2048];
            File newFile = new File("E:\\new\\" + newName);
            if (newFile.createNewFile()){
                FileOutputStream fileOutputStream = new FileOutputStream(newFile);
                int i = 0;
                while(i != -1){
                    i = fileInputStream.read(bytes);
                    fileOutputStream.write(bytes);
                }
            }
        }
    }

}
