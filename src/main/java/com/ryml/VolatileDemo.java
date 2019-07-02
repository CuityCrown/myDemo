package com.ryml;

import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;




public class VolatileDemo {
    private static  boolean isStop = false;


/*    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("出狱倒计时");
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
            isStop = true;
            System.out.println("批准出狱!");
        }).start();
        while (!isStop) {

        }
    }*/

    @Test
    public void test(){
        //获取当前已使用的内存大小
        long size = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
        /**
         * 获取配置的java堆内存大小
         */
        long thresholdSize = (long) (ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax() * 0.7) ;
        System.out.println(size);
        System.out.println(thresholdSize);
    }

    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    list.add(1);
                    list.get(0);
                    list.remove(0);
                    list.set(1,1);
                }
            }).start();
        }
        Thread.sleep(10000);
        System.out.println(list+"测试"+list.size());
    }

}