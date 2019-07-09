package com.ryml.cache;

import org.springframework.cache.Cache;
import org.springframework.lang.Nullable;

import java.util.concurrent.Callable;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/7/9
 */
public class SpringCache implements Cache{

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Object getNativeCache() {
        return null;
    }

    @Nullable
    @Override
    public ValueWrapper get(Object o) {
        return null;
    }

    @Nullable
    @Override
    public <T> T get(Object o, @Nullable Class<T> aClass) {
        return null;
    }

    @Nullable
    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    @Override
    public void put(Object o, @Nullable Object o1) {

    }

    @Nullable
    @Override
    public ValueWrapper putIfAbsent(Object o, @Nullable Object o1) {
        return null;
    }

    @Override
    public void evict(Object o) {

    }

    @Override
    public void clear() {

    }
}
