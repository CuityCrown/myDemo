package com.ryml.test;

import com.google.common.collect.Lists;
import com.ryml.controller.TestController;
import com.ryml.util.Dog;
import com.ryml.util.MyApplicationContextUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

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
    private static final Logger logger = LoggerFactory.getLogger(MyApplicationTest.class);

    private final static String ROOT_PATH_LOCK = "rootlock";

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Autowired
    private CuratorFramework curatorFramework;

    @Autowired
    private TestController testController;

    /**
     * 获取分布式锁
     */
    public void acquireDistributedLock(String path) {
        String keyPath = "/" + ROOT_PATH_LOCK + "/" + path;
        while (true) {
            try {
                curatorFramework
                        .create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL)
                        .forPath(keyPath);
                logger.info("success to acquire lock for path:{}", keyPath);
                break;
            } catch (Exception e) {
                logger.info("failed to acquire lock for path:{}", keyPath);
                logger.info("while try again .......");
                try {
                    if (countDownLatch.getCount() <= 0) {
                        countDownLatch = new CountDownLatch(1);
                    }
                    countDownLatch.await();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 释放分布式锁
     */
    public boolean releaseDistributedLock(String path) {
        try {
            String keyPath = "/" + ROOT_PATH_LOCK + "/" + path;
            if (curatorFramework.checkExists().forPath(keyPath) != null) {
                curatorFramework.delete().forPath(keyPath);
            }
        } catch (Exception e) {
            logger.error("failed to release lock");
            return false;
        }
        return true;
    }

    /**
     * 创建 watcher 事件
     */
    private void addWatcher(String path) throws Exception {
        String keyPath;
        if (path.equals(ROOT_PATH_LOCK)) {
            keyPath = "/" + path;
        } else {
            keyPath = "/" + ROOT_PATH_LOCK + "/" + path;
        }
        final PathChildrenCache cache = new PathChildrenCache(curatorFramework, keyPath, false);
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                curatorFramework.delete().deletingChildrenIfNeeded().forPath(keyPath);

            }
        });
    }

    public void test() throws Exception {
        final PathChildrenCache cache = new PathChildrenCache(curatorFramework,"",false);
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cache.getListenable().addListener((client,event)->{
            if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)) {
                String oldPath = event.getData().getPath();
                logger.info("success to release lock for path:{}", oldPath);
                if (oldPath.contains("")) {
                    //释放计数器，让当前的请求获取锁
                    countDownLatch.countDown();
                }
            }
        });
    }

    public void afterPropertiesSet() {
        curatorFramework = curatorFramework.usingNamespace("lock-namespace");
        String path = "/" + ROOT_PATH_LOCK;
        try {
            if (curatorFramework.checkExists().forPath(path) == null) {
                curatorFramework.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(path);
            }
            addWatcher(ROOT_PATH_LOCK);
            logger.info("root path 的 watcher 事件创建成功");
        } catch (Exception e) {
            logger.error("connect zookeeper fail，please check the log >> {}", e.getMessage(), e);
        }
    }

    @Autowired
    private MyApplicationContextUtils myApplicationContextUtils;

    @Test
    public void test1(){
        System.out.println(myApplicationContextUtils.test());
    }

    @Test
    public void test2() {
        validate(Dog.builder()
                .id(1)
                .ammount(240D)
                .gender(false)
                .integers(Lists.newArrayList(1,2,3))
                .name("王屁呆")
                .phoneNum("15603908486")
                .build());
    }

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private void validate(Dog dog) {
        Map map = new ConcurrentHashMap();
        map.put(11,1);
        Set<ConstraintViolation<Dog>> validate = validator.validate(dog,Default.class);
        for (ConstraintViolation<Dog> dogConstraintViolation : validate) {
            String message = dogConstraintViolation.getMessage();
            System.out.println(message);
        }
    }

}

