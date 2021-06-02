package com.ryml.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ryml.entity.EmployeeBean;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2020/4/22 12:43
 */
public class MyDemo {


    public static void main(String[] args) {
        final List<String> number = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        final List<String> strings = Arrays.asList("a", "b", "c", "d", "e");
        final AtomicInteger atomicInteger = new AtomicInteger(1);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(() -> {
            int i = 0;
            while (i < 10) {
                if (atomicInteger.intValue() % 3 != 0) {
                    System.out.println(number.get(i++));
                    atomicInteger.incrementAndGet();
                }
                countDownLatch.countDown();
            }
        }).start();

        new Thread(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i = 0;
            while (i < 5) {
                if (atomicInteger.intValue() % 3 == 0) {
                    System.out.println(strings.get(i++));
                    atomicInteger.incrementAndGet();
                }
            }
        }).start();
    }


    @Test
    public void test(){
        String json = "[{\"did\":1000,\"id\":1,\"name\":\"person1\",\"salary\":2000},{\"did\":1000,\"id\":2,\"name\":\"person2\",\"salary\":2100},{\"did\":1001,\"id\":3,\"name\":\"person3\",\"salary\":2200},{\"did\":1002,\"id\":4,\"name\":\"person4\",\"salary\":2300},{\"did\":1000,\"id\":5,\"name\":\"person5\",\"salary\":2400},{\"did\":1001,\"id\":6,\"name\":\"person6\",\"salary\":2500},{\"did\":1000,\"id\":7,\"name\":\"person7\",\"salary\":2600},{\"did\":1001,\"id\":8,\"name\":\"person8\",\"salary\":2700},{\"did\":1001,\"id\":9,\"name\":\"person9\",\"salary\":2800},{\"did\":1002,\"id\":10,\"name\":\"person10\",\"salary\":2900}]";
        List<EmployeeBean> employeeBeans = JSONObject.parseObject(json, new TypeReference<List<EmployeeBean>>() {
        });
        Map<Integer, List<EmployeeBean>> employeeBeansMap = employeeBeans.stream().collect(Collectors.groupingBy(EmployeeBean::getDid));
        Set<Integer> dids = employeeBeansMap.keySet();
        for (Integer did : dids) {
            System.out.println("部门id= " + did);
            List<EmployeeBean> employeeBeans1 = employeeBeansMap.get(did);
            int sumSalary = employeeBeans1.stream().mapToInt(EmployeeBean::getSalary).sum();
            System.out.println("职员工资总和" + sumSalary);
        }
    }
}
