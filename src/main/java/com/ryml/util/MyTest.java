package com.ryml.util;

import org.apache.tomcat.util.threads.TaskThreadFactory;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Test
    public  void test() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Stream<Integer> stream = integers.stream();
        List<Integer> collect = stream.filter(MyTest::add).collect(Collectors.toList());
        System.out.println(collect);
    }

    public static boolean add(Integer i){
        return i == 1;
    }

}
