package com.ryml.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/7/4
 */
public class TestApplication implements ApplicationContextAware{

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MyApplicationContextUtils.test(applicationContext);
    }

}
