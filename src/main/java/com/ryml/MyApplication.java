package com.ryml;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/17
 */
@SpringBootApplication
//@EnableDubbo
@MapperScan("/com/ryml/mapper")
@EntityScan("/com/ryml/entity")
public class MyApplication {
    public static void main(String[] args) {

        SpringApplication.run(MyApplication.class,args);
    }
}
