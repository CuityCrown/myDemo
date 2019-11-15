package com.ryml.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/9/23 17:03
 */
public class CodeModelTest {

    @Test
    public void test(){
        JedisPool jedisPool = new JedisPool("localhost",6379);
        Jedis resource = jedisPool.getResource();
        String s = resource.get("1");
        System.out.println(s);
    }

    @Test
    public void test1() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD-MON-YY");
        Date parse = simpleDateFormat.parse("30-Nov-17");
        System.out.println(parse);
    }

}
