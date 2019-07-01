package com.ryml.config.zookeeper;

import lombok.Data;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/7/1
 */
@Data
public class MyZookeeperConfig {

    private Integer retryCount;

    private Integer elapsedTimeMs;

    private String coonectString;

    private Integer sessionTimeoutMs;

    private Integer connectionTimeoutMs;

}
