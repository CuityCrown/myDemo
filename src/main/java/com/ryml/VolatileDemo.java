package com.ryml;

import java.util.concurrent.TimeUnit;




public class VolatileDemo {
    private static  boolean isStop = false;


    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("出狱倒计时");
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
            isStop = true;
            System.out.println("批准出狱!");
        }).start();
        while (!isStop) {

        }
    }



}