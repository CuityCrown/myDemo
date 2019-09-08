package com.ryml.demo.Proxy;

import com.ryml.entity.Student;
import com.ryml.service.impl.StudentServiceImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/9/8 17:36
 */
public class MyCglibProxyTest {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(StudentServiceImpl.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("123");
                return methodProxy.invokeSuper(o,objects);
            }
        });
        StudentServiceImpl studentService = (StudentServiceImpl) enhancer.create();
        List<Student> all = studentService.getAll();
        System.out.println(all);
    }

}
