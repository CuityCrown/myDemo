package com.ryml.test;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @Description:
 * @author: 刘一博
 * @date: 2024/5/7 14:46
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<CompletableFuture<Demo>> demoFutureList = new ArrayList<>();
        demoFutureList.add(CompletableFuture.supplyAsync(CompletableFutureTest::getDemo));
        demoFutureList.add(CompletableFuture.supplyAsync(CompletableFutureTest::getDemo));
        demoFutureList.add(CompletableFuture.supplyAsync(CompletableFutureTest::getDemo));
        demoFutureList.add(CompletableFuture.supplyAsync(CompletableFutureTest::getDemo));
        demoFutureList.add(CompletableFuture.supplyAsync(CompletableFutureTest::getDemo));
        CompletableFuture<List<Demo>> listCompletableFuture = CompletableFuture.allOf(demoFutureList.toArray(new CompletableFuture<?>[0]))
                .thenApply(v -> demoFutureList.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList())
                );
        System.out.println(JSONObject.toJSONString(listCompletableFuture.get()));
    }

    public static Demo getDemo() {
        Demo demo = new Demo();
        demo.setName("lyb");
        demo.setId(1);
        return demo;
    }

}
