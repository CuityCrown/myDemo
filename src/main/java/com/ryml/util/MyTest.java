package com.ryml.util;

import org.apache.tomcat.util.threads.TaskThreadFactory;

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

    public static void main(String[] args) throws InterruptedException {
        final MyThreadPoolExectuor myThreadPoolExectuor = new MyThreadPoolExectuor(10, 20, 3000, TimeUnit.MINUTES,
                new LinkedBlockingQueue<Runnable>(), new TaskThreadFactory("1", true, 1), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            }
        });

        myThreadPoolExectuor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(myThreadPoolExectuor.getSubmittedTasksCount());
            }
        });

        Thread.sleep(3000);
        System.out.println(myThreadPoolExectuor.getSubmittedTasksCount());
    }

}
