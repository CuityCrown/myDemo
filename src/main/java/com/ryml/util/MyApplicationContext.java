package com.ryml.util;

import com.ryml.controller.TestController;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/7/4
 */
public class MyApplicationContext {

    private static final List<String> classNames = new ArrayList<>();

    private static final Map<String, Object> beans = new HashMap<String, Object>();

    public static void initMyApplicationContext(){
        doScanner("com.ryml");
        doInstance();
    }

    private static void doScanner(String scanPackage) {
        URL url = new MyApplicationContext().getClass().getClassLoader().getResource(scanPackage.replaceAll("\\.", "/"));

        File file = new File(url.getFile());

        File[] files = file.listFiles();

        for (File file1 : files) {
            if (file1.isDirectory()) {
                doScanner(scanPackage + "." + file1.getName());
            } else {
                if (!file1.getName().endsWith(".class")) {
                    continue;
                }
                classNames.add(scanPackage + "." + file1.getName().replace(".class", ""));
            }
        }
    }


    private static void doInstance() {
        if (classNames.isEmpty()) {
            return;
        }

        try {

            for (String className : classNames) {

                Class<?> beanClass = Class.forName(className);

                if (beanClass.isAnnotationPresent(Controller.class)) {
                    Controller annotation = beanClass.getAnnotation(Controller.class);
                    String value = annotation.value();
                    Object bean = beanClass.newInstance();
                    if (value.length() > 0) {
                        beans.put(value, bean);
                    } else {
                        String beanName = beanClass.getSimpleName();
                        beans.put(makeFirstLetterToLowercase(beanName), bean);
                    }
                } else if (beanClass.isAnnotationPresent(Service.class)) {
                    Service annotation = beanClass.getAnnotation(Service.class);
                    String value = annotation.value();
                    Object bean = beanClass.newInstance();
                    if (value.length() > 0) {
                        beans.put(value, bean);
                    } else {
                        String beanName = beanClass.getSimpleName();
                        beans.put(makeFirstLetterToLowercase(beanName), bean);
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static String makeFirstLetterToLowercase(String s){
        if(Character.isLowerCase(s.charAt(0))){
            return s;
        }else{
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    public static Object getBean(String beanName){
        return beans.get(beanName);
    }

    public static void main(String[] args) {
        MyApplicationContext.initMyApplicationContext();
        TestController testController = (TestController) MyApplicationContext.getBean("testController");
        System.out.println(testController);
    }

}
