package com.ryml.config.servlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/8/1
 */
public class MyDispatcherServler extends DispatcherServlet{

    public MyDispatcherServler() {
        super();
    }

    public MyDispatcherServler(WebApplicationContext webApplicationContext) {
        super(webApplicationContext);
    }

    @Override
    protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.doService(request, response);
    }
}
