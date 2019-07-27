package com.ryml.util;

import java.util.Arrays;
import java.util.List;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/7/25
 */
public class MyPasswordUtils {
    public static void main(String[] args) {
        final List<String> listOne = Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");
        final List<String> listTwo = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
        final List<Integer> listThree = Arrays.asList(0,1,2,3,4,5,6,7,8,9);
        final List<String> listFour = Arrays.asList(".","!","@","#","$","%","^","&","*","","");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (String s : listOne) {
                    getPassword(s);
                }
                for (String s : listTwo) {
                    getPassword(s);
                }
                for (Integer s : listThree) {
                    getPassword(s+"");
                }
                for (String s : listFour) {
                    getPassword(s);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (String s : listOne) {

                }
            }
        }).start();
    }

    public static void getPassword(String value){
        if (value.equals("5211314")){
            System.out.println(value);
        }
    }
}
