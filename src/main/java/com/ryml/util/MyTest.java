package com.ryml.util;

import org.apache.tomcat.util.threads.TaskThreadFactory;

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
public class MyTest {

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
        System.out.println("v_1_0".substring(2,3));
    }

    public static String getDateLastDay(String year, String month) {

        //year="2018" month="2"
        Calendar calendar = Calendar.getInstance();
        // 设置时间,当前时间不用设置
        calendar.set(Calendar.YEAR, Integer.parseInt(year));
        calendar.set(Calendar.MONTH, Integer.parseInt(month));

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
        return format.format(calendar.getTime());
    }


}
