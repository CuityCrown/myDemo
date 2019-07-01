package com.ryml;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        String s = curatorFramework.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).
                forPath("/data", "https://www.cnblogs.com/yjmyzz/p/zookeeper-acl-demo.html".getBytes());
        System.out.println(s);
        byte[] bytes = curatorFramework.getData().forPath("/data");

    }

    @Test
    public void testZkGetData() throws Exception {
        byte[] bytes = curatorFramework.getData().forPath("/data");
        System.out.println(new String(bytes));
    }

}
