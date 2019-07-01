package com.ryml.config.zookeeper;

import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description:Zookeeper参数配置类
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/7/1
 */
@Configuration
public class ZookeeperConfiguration {

    @Value("${curator.retryCount}")
    private Integer retryCount;

    @Value("${curator.elapsedTimeMs}")
    private Integer elapsedTimeMs;

    @Value("${curator.coonectString}")
    private String coonectString;

    @Value("${curator.sessionTimeoutMs}")
    private Integer sessionTimeoutMs;

    @Value("${curator.connectionTimeoutMs}")
    private Integer connectionTimeoutMs;

    @Bean
    public CuratorZookeeperClient testCurator() throws Exception {
        CuratorZookeeperClient curatorZookeeperClient = new CuratorZookeeperClient(coonectString, connectionTimeoutMs, sessionTimeoutMs, null, new RetryPolicy() {
            @Override
            public boolean allowRetry(int i, long l, RetrySleeper retrySleeper) {
                return false;
            }
        });
        curatorZookeeperClient.start();
        return curatorZookeeperClient;
    }

}
