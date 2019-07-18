package com.ryml;

import com.ryml.enums.RedisCommonEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
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
    public void testTransaction(){
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        redisTemplate.multi();
        valueOperations.set("name","张三");
        valueOperations.set("age",18);
        valueOperations.set("sex","男");
        redisTemplate.exec();
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
class woshi{
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6379);
        Socket socket = server.accept();
        byte[] chars = new byte[64];
        socket.getInputStream().read(chars);
        System.out.println(new String(chars));
    }
}
class nicai{
    public static void main(String[] args) throws IOException {
        Jedis jedis = new Jedis();
        jedis.set("","");
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost",6379),1000);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("*3".getBytes());
        //命令
        outputStream.write("$3".getBytes());
        outputStream.write("SET".getBytes());
        //key
        outputStream.write("$1".getBytes());
        outputStream.write("1".getBytes());
        //value
        outputStream.write("$6".getBytes());
        outputStream.write("测试".getBytes());
        outputStream.flush();
    }
}
