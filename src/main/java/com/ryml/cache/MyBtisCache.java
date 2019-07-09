package com.ryml.cache;

import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/7/9
 */
@Service
public class MyBtisCache implements Cache{

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void putObject(Object key, Object value) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key,value);
    }

    @Override
    public Object getObject(Object key) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    @Override
    public Object removeObject(Object key) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
