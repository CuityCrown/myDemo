/*
package com.ryml.config.redis;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

*/
/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/17
 *//*

@Configuration
public class RedisConfiguration {

    @Bean
    @ConfigurationProperties(value = "redis.jedis.config")
    public MyJedisPoolConfig testJedisPoolConfig() {
        return new MyJedisPoolConfig();
    }

    @Bean
    public JedisPool testJedisPool(@Autowired MyJedisPoolConfig myJedisPoolConfig) {
        return new JedisPool(myJedisPoolConfig, myJedisPoolConfig.getHost(), myJedisPoolConfig.getPort());
    }

}
*/
