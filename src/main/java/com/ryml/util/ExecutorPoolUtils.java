package com.ryml.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/9/29 17:52
 */
public class ExecutorPoolUtils {

    private static ExecutorService threadPoolPond;

    static {
        threadPoolPond = new ThreadPoolExecutor(100, 200, 60,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10000), r -> {
            Thread thread = new Thread(r);
            thread.setName("EsPool");
            return thread;
        });
    }

    public static void submit(Runnable runnable) {
        threadPoolPond.submit(runnable);
    }

    public static void execute(Runnable runnable) {
        threadPoolPond.execute(runnable);
    }

}
