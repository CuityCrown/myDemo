package com.ryml;

import com.ryml.enums.RedisCommonEnum;
import com.ryml.util.MyTest;
import org.apache.curator.CuratorZookeeperClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyApplicationTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CuratorZookeeperClient curatorZookeeperClient;

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
                valueOperations.set(1,1,30000,TimeUnit.SECONDS);
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

    @Test
    public void test2() throws Exception {
        ZooKeeper zooKeeper = curatorZookeeperClient.getZooKeeper();
        long sessionId = zooKeeper.getSessionId();
        int sessionTimeout = zooKeeper.getSessionTimeout();
        System.out.println(sessionId+"测试sessionId");
        System.out.println(sessionId+"测试sessionTimeout");
        zooKeeper.create("/data","http://localhost:8094/student/getMenu".getBytes(),null, CreateMode.EPHEMERAL);
    }

    @Test
    public void test4() throws Exception {
        ZooKeeper zooKeeper = curatorZookeeperClient.getZooKeeper();
        byte[] data = zooKeeper.getData("/data", null, null);
        System.out.println(data.toString());
    }

    @Test
    public void test3() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Stream<Integer> stream = integers.stream();
        List<Integer> collect = stream.filter(MyApplicationTest::add).collect(Collectors.toList());
        System.out.println(collect);
    }

    public static boolean add(Integer i){
        return i == 1;
    }

}

