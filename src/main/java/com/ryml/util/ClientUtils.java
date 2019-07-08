package com.ryml.util;

import com.ryml.MyApplication;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/7/8
 */
public class ClientUtils {

    public static <T> T client(TypeRef<T> typeRef){
        T result = (T) test();
        return result;
    }

    public static MyTest test(){
        MyTest myTest = new MyTest();
        myTest.setRe(new MyApplication());
        return myTest;
    }

    public static void main(String[] args) {
        System.out.println(ClientUtils.client(new TypeRef<MyTest<MyApplication>>()));
    }

}
