package com.ryml;

import com.alibaba.fastjson.JSONObject;
import com.ryml.entity.Dog;
import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
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
import java.util.Arrays;
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
    public void Test2() throws Exception {
        //String s = curatorFramework.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/lock");
        String s1 = curatorFramework.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/lock/");
        String s2 = curatorFramework.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/lock/");
        //System.out.println(s);
        System.out.println(s1);
        System.out.println(s2);
        List<String> strings = curatorFramework.getChildren().forPath("/lock");
        for (int i = 0; i < strings.size(); i++) {
            if (s1.equals("/lock/"+strings.get(i))){
                System.out.println(strings.get(i));
                if (i == 0){
                    //获取锁

                    //删除节点

                }else{
                    //设置监听事件
                    curatorFramework.getData().usingWatcher(new CuratorWatcher() {
                        @Override
                        public void process(WatchedEvent event) throws Exception {
                            //回调
                            //删除节点
                        }
                    }).forPath("/lock/"+strings.get(i-1));
                }
            }
        }
        System.out.println(strings);
    }

    @Test
    public void test1(){
        Dog dog = new Dog();
        dog.setAge(18);
        dog.setName("测试");
        dog.setId(1);
        Object obj = dog;
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(obj));
        Object name = jsonObject.get("age");
        System.out.println(name);
    }


}

