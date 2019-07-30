package com.ryml;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/7/29
 */
public class MyDemoTest {
    private final static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("order-event-publisher-pool-%d").build();
    private final static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10000), namedThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Future<String>> list = new ArrayList<>();
        for (int i = 0;i< 10;i++){
            Future<String> submit = threadPool.submit(() -> {
                System.out.println("测试");
                return "测试2";
            });
            list.add(submit);
        }
        System.out.println("我是测试");
        for (Future<String> stringFuture : list) {
            System.out.println(stringFuture.get());
        }
    }

    @Test
    public void test(){
        Future<Object> submit = threadPool.submit(() -> {
            throw new RuntimeException("执行出错");
        });
        try {
            submit.get();
            System.out.println("测试");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1(){
        try{
            Thread thread = new Thread(()->{
                throw new RuntimeException("测试执行出错");
            });
            thread.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
