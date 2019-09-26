package com.ryml.config.web;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/9/26 17:38
 */
public class MyHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("My name is handelInterceptor , I already invoke" + handler.toString());
        return true;
    }

}
