package com.ryml.util;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/7/4
 */
@Service
public class MyApplicationContextUtils{

    @Autowired
    private ApplicationContext applicationContext;

    public Object test(){
        return applicationContext.getBean("myApplicationContextUtils");
    }
}

