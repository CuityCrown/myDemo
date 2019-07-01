package com.ryml;

import com.ryml.enums.RedisCommonEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * description:redis测试类
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/7/1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test(){
        ValueOperations<String, Integer> stringIntegerValueOperations = redisTemplate.opsForValue();
        stringIntegerValueOperations.set(RedisCommonEnum.STUDENT.getValue(),1234123);
        System.out.println("测试======"+stringIntegerValueOperations.get(RedisCommonEnum.STUDENT.getValue()));
    }

    @Test
    public void test1() throws InterruptedException {
        ValueOperations<Integer,Integer> valueOperations = redisTemplate.opsForValue();
        new Thread(new Runnable() {
            @Override
            public void run() {
                valueOperations.set(1,1,30000, TimeUnit.SECONDS);
                Integer integer = valueOperations.get(1);
                System.out.println(integer+"我是线程1");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Integer integer = valueOperations.get(1);
                System.out.println(integer+"我是线程2");
                if (integer != null){
                    System.out.println("我没有获取到锁");
                }
            }
        }).start();

        Thread.sleep(10000);
        System.out.println("主线程结束");
    }

}