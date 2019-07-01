package com.ryml.config.zookeeper;

import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.sql.DataSourceDefinition;

/**
 * description:Zookeeper参数配置类
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/7/1
 */
@Configuration
public class ZookeeperConfiguration {

/*    @Value("${curator.retryCount}")
    private Integer retryCount;

    @Value("${curator.elapsedTimeMs}")
    private Integer elapsedTimeMs;

    @Value("${curator.coonectString}")
    private String coonectString;

    @Value("${curator.sessionTimeoutMs}")
    private Integer sessionTimeoutMs;

    @Value("${curator.connectionTimeoutMs}")
    private Integer connectionTimeoutMs;*/

    @Bean
    @ConfigurationProperties(value = "curator")
    public MyZookeeperConfig testMyZookeeperConfig(){
        return new MyZookeeperConfig();
    }

    @Bean(initMethod = "start")
    public CuratorFramework testCurator(@Autowired MyZookeeperConfig myZookeeperConfig) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(myZookeeperConfig.getCoonectString(),
            myZookeeperConfig.getSessionTimeoutMs(),myZookeeperConfig.getConnectionTimeoutMs(),
                new RetryNTimes(myZookeeperConfig.getRetryCount(),myZookeeperConfig.getElapsedTimeMs()));
        return curatorFramework;
    }

}
