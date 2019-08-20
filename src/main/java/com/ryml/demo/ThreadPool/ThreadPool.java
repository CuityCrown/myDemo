package com.ryml.demo.ThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/8/15 23:09
 */
public class ThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
    }

}
