package com.ryml.test;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/9/25 17:08
 */
public class ThreadPoolTest {

    @Test
    public void test(){
        List<Map<String, Object>> result = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        AtomicInteger atomicInteger = new AtomicInteger(1);
        for (int i = 1; i < 1001; i++) {
            new Thread(() -> {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("pageNumber",atomicInteger.getAndIncrement());
                    List<Map<String, Object>> list = new ArrayList<>();
                    if (!list.isEmpty()) {
                        result.addAll(list);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
