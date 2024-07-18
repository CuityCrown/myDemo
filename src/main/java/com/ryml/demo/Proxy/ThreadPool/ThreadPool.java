package com.ryml.demo.Proxy.ThreadPool;

import com.ryml.entity.Menu;
import com.ryml.util.ExecutorPoolUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/8/15 23:09
 */
public class ThreadPool {

    @Test
    public void testThreadPoolExecutor() {
        long start = System.currentTimeMillis();
        List<Menu> search = Collections.synchronizedList(new ArrayList<>());


        CountDownLatch countDownLatch = new CountDownLatch(search.size());
        AtomicInteger atomicInteger = new AtomicInteger(1);
        System.out.println(search.size() + "开启数量");
        for (Menu lenovoBean : search) {
            ExecutorPoolUtils.execute(() -> {
                try {
                    atomicInteger.getAndIncrement();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
        }
        long end = System.currentTimeMillis();
        System.out.println("执行时间为" + ((end - start)));
    }

}
