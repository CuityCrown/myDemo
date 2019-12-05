package com.ryml.aop;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/11/27 17:16
 */
@Aspect
@Component
public class RecordExecutionTime {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecordExecutionTime.class);

    @Pointcut("execution(* com.ryml.controller.*.*(..))")
    public void controllerAspect(){}

    @Around("controllerAspect()")
    public Object aroundRemote(ProceedingJoinPoint jp) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        long start = System.currentTimeMillis();
        Object obj = null;
        try {
            obj = jp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long end = System.currentTimeMillis();
        LOGGER.error("接口"+request.getRequestURL()+"执行结束"+(end-start)+"ms"+"入参:"+ JSONObject.toJSONString(jp.getArgs()));
        return obj;
    }

}
