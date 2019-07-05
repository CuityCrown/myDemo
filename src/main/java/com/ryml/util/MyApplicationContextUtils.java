package com.ryml.util;

import org.springframework.context.ApplicationContext;
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

    private static ApplicationContext applicationContext;

    public static void test(ApplicationContext applicationContext){
        MyApplicationContextUtils.applicationContext = applicationContext;
    }

    public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }
}

