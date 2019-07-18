package com.ryml;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * description:zookeeper测试类
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/7/1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ZkApplicationTest {

    @Autowired
    private CuratorFramework curatorFramework;

    @Test
    public void testZkCreate() throws Exception {
        String s = curatorFramework.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).
                forPath("/data", "https://www.cnblogs.com/yjmyzz/p/zookeeper-acl-demo.html".getBytes());
        System.out.println(s);
    }

    @Test
    public void testZkGetData() throws Exception {
        byte[] bytes = curatorFramework.getData().forPath("/data");
        System.out.println(new String(bytes));
    }

    @Test
    public void testGetChildren() throws Exception {
        List<String> childes = curatorFramework.getChildren().forPath("/data");
        System.out.println(childes);
    }

    @Test
    public void Test2(){

    }


}

