package com.ryml;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * description:JDK1.8新特性测试类
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/7/1
 */
public class JDK8Test {

    @Test
    public void testStream() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Stream<Integer> stream = integers.stream();
        List<Integer> collect = stream.filter(JDK8Test::add).collect(Collectors.toList());
        System.out.println(collect);
    }

    public static boolean add(Integer i){
        return i == 1;
    }

}
