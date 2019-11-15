package com.ryml.util;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/11/13 17:07
 */
public class ThreadLocalUtils {

    private static final ThreadLocal<Object> THREAD_LOCAL = new ThreadLocal<>();

    public static void setContext(Object context){
        THREAD_LOCAL.set(context);
    }

    public static Object getContext(){
        return THREAD_LOCAL.get();
    }

    public static void destroy(){
        THREAD_LOCAL.remove();
    }

}
