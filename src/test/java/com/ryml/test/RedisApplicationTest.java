package com.ryml.test;

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
import java.io.InputStream;
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
        ServerSocket server = new ServerSocket(6380);
        Socket socket = server.accept();
        byte[] chars = new byte[64];
        socket.getInputStream().read(chars);
        System.out.println(new String(chars));
    }
}
class nicai{
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost",6379));
        OutputStream outputStream = socket.getOutputStream();
        StringBuffer stringBuffer = new StringBuffer();
        String value = "你猜啊啊啊啊";
        stringBuffer.append("*3").append("\r\n");
        //命令
        stringBuffer.append("$3").append("\r\n");
        stringBuffer.append("SET").append("\r\n");
        //key
        stringBuffer.append("$1").append("\r\n");
        stringBuffer.append("1").append("\r\n");
        //value
        stringBuffer.append("$").append(value.getBytes().length).append("\r\n");
        stringBuffer.append(value).append("\r\n");
        outputStream.write(stringBuffer.toString().getBytes());
        outputStream.flush();
        outputStream.close();
        socket.close();
    }
}
class read{
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost",6379));
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("*2").append("\r\n");
        //命令
        stringBuffer.append("$3").append("\r\n");
        stringBuffer.append("GET").append("\r\n");
        //key
        stringBuffer.append("$1").append("\r\n");
        stringBuffer.append("1").append("\r\n");
        outputStream.write(stringBuffer.toString().getBytes());
        byte[] b = new byte[4096];
        inputStream.read(b);
        System.out.println(new String(b));
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        socket.close();
    }
}

