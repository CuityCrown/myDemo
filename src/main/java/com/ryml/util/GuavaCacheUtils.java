package com.ryml.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @author: 刘一博
 * @date: 2024/7/18 13:25
 */
public class GuavaCacheUtils {

    /**
     * 本地缓存
     */
    private static Cache<String, Optional<Dog>> localCache = CacheBuilder
            .newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(720, TimeUnit.MINUTES)
            .removalListener(notification -> {

            })
            .build();

    public static void main(String[] args) {
        localCache.put("ces", Optional.of(Dog.builder().id(1).build()));
        localCache.invalidate("ces");
        Optional<Dog> ces = localCache.getIfPresent("ces");
        if (ces != null) {
            System.out.println(ces.get());
        }
    }
}
