package com.ryml.demo.Proxy;

import com.ryml.service.StudentService;
import com.ryml.service.impl.StudentServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/9/8 17:34
 */
public class MyJdkProxyTest{

    public static void main(String[] args) {
        StudentServiceImpl studentService = new StudentServiceImpl();
        StudentService proxy = (StudentService) Proxy.newProxyInstance(MyJdkProxyTest.class.getClassLoader(), new Class[]{StudentService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("动态代理执行");
                return method.invoke(studentService, args);
            }
        });
        System.out.println(proxy.getAll());
        System.out.println(proxy.getClass());
    }

}
