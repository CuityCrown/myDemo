package com.ryml.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/17
 */
public class RedisUtils {

    private static JedisPool jedisPool;

    static{
        //1 获得连接池配置对象，设置配置项
        JedisPoolConfig config = new JedisPoolConfig();
        // 1.1 最大连接数
        config.setMaxTotal(30);
        //1.2 最大空闲连接数
        config.setMaxIdle(10);
        //获得连接池
        jedisPool = new JedisPool(config,"152.136.133.147",6379);
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

}
