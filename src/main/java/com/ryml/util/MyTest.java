package com.ryml.util;

import org.apache.tomcat.util.threads.TaskThreadFactory;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/29
 */
public class MyTest<T> {

    private T re;

/*    public static void main(String[] args) throws InterruptedException {
        final MyThreadPoolExectuor myThreadPoolExectuor = new MyThreadPoolExectuor(10, 20, 3000, TimeUnit.MINUTES,
                new LinkedBlockingQueue<Runnable>(), new TaskThreadFactory("1", true, 1));

        for (int i = 0; i < 30000; i++) {
            myThreadPoolExectuor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(myThreadPoolExectuor.getSubmittedTasksCount());
                }
            });
        }
        Thread.sleep(3000);
        System.out.println(myThreadPoolExectuor.getSubmittedTasksCount());
    }*/

    public static void main(String[] args) throws ParseException {
        String versionNow = "v_20_20";
        String s = addVersionNumber(versionNow);
        System.out.println(s);
    }

    private static String addVersionNumber(String versionNow){
        StringBuilder stringBuilder = new StringBuilder("");

        //拿到需要截取父级版本号的结尾下标和子级版本号的开头下标
        int i = versionNow.lastIndexOf("_");

        //截取子级版本号并+1
        String subNumber = versionNow.substring(i+1,versionNow.length());
        Integer subNumberInteger = Integer.valueOf(subNumber)+1;

        //截取父级版本号
        String faNumber = versionNow.substring(2,i);
        Integer faNumberInteger = Integer.valueOf(faNumber);

        //如果子级版本号大于20父级版本号+1
        if (subNumberInteger > 20){
            stringBuilder.append("v_").append((faNumberInteger+1)).append("_0");
        }else{
            //否则子级版本号+1父级版本号不做改变
            stringBuilder.append("v_").append(faNumberInteger).append("_").append(subNumberInteger);
        }
        return stringBuilder.toString();
    }

    public T getRe() {
        return re;
    }

    public void setRe(T re) {
        this.re = re;
    }

    @Override
    public String toString() {
        return "MyTest{" +
                "re=" + re +
                '}';
    }
}
