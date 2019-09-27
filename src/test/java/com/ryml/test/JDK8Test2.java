package com.ryml.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ryml.entity.Trader;
import com.ryml.entity.Transaction;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/9/20 11:23
 */
public class JDK8Test2 {

    private static List<Transaction> list;

    private static Map<String, Object> map;

    static {
        Trader liuyb = new Trader("liuyb", "beijing");
        Trader maosl = new Trader("maosl", "beijing");
        Trader liyh = new Trader("liyh", "nanjing");
        Trader zongps = new Trader("zongps", "zhengzhou");
        Trader liry = new Trader("liry", "shanghai");
        list = Arrays.asList(
                new Transaction(liuyb, 2011, 5000),
                new Transaction(liuyb, 2011, 3000),
                new Transaction(maosl, 2012, 4000),
                new Transaction(maosl, 2012, 3000),
                new Transaction(liyh, 2011, 3000),
                new Transaction(zongps, 2012, 2000),
                new Transaction(liry, 2012, 1000)
        );
        map = new HashMap<>();
        map.put("liuyb", liuyb);
        map.put("maosl", maosl);
        map.put("liyh", liyh);
        map.put("zongps", zongps);
        map.put("liry", liry);
    }

    @Test
    public void findYear() {
        List<Integer> collect = list.stream().map(Transaction::getAmount).sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void finCity() {
        List<String> collect = list.stream().map(ts -> ts.getTrader().getCity()).distinct().collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void findTrader() {
        List<Trader> collect = list.stream().filter(ts -> "beijing".equals(ts.getTrader().getCity())).distinct().
                map(Transaction::getTrader).sorted(Comparator.comparing(Trader::getName)).limit(3).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void findTraderName() {
        String collect = list.stream().map(ts -> ts.getTrader().getName()).distinct().sorted(Comparator.naturalOrder()).collect(Collectors.joining());
        System.out.println(collect);
    }

    @Test
    public void findWorkInBeijing() {
        boolean b = list.stream().anyMatch(ts -> "beijing".equals(ts.getTrader().getCity()));
        System.out.println(b);
    }

    @Test
    public void findWorkInBeijingAmount() {
        int sum = list.stream().filter(ts -> "beijing".equals(ts.getTrader().getCity())).mapToInt(Transaction::getAmount).sum();
        System.out.println(sum);
    }

    @Test
    public void findMaxOrMinAmount() {
        list.stream().mapToInt(Transaction::getAmount).min().ifPresent(System.out::println);
        list.stream().mapToInt(Transaction::getAmount).max().ifPresent(System.out::println);
    }

    @Test
    public void testUpdateStreamType() {
        list.stream()
                .parallel() //转换为并发流
                .sequential(); //转换成普通流
    }

    @Test
    public void testParallelStream() {
        map.values().parallelStream().forEach(System.out::println);
        System.out.println("===================");
        map.values().parallelStream().forEach(System.out::println);
    }

    @Test
    public void testGenerateAndIterate() {
        List<Double> collect = Stream.generate(Math::random).limit(10).collect(Collectors.toList());
        System.out.println(collect);
        List<Integer> collect1 = Stream.iterate(1, n -> {
            System.out.println(n);
            return n + 1;
        }).limit(13).collect(Collectors.toList());
        System.out.println(collect1);
    }

    @Test
    public void testGroupBy() {
        Map<String, List<Transaction>> collect = list.stream().collect(Collectors.groupingBy(n -> n.getTrader().getCity()));
        System.out.println(collect);
    }

    @Test
    public void testPattionBy() {
        Map<Boolean, List<Transaction>> collect = list.stream().collect(Collectors.partitioningBy(n -> Boolean.parseBoolean(n.getTrader().getName())));
        System.out.println(collect);
    }

    @Test
    public void testToMap() {
        //Map<Integer, Transaction> collect = list.stream().collect(Collectors.toMap(Transaction::getYear, v -> v));
        //做key重复策略处理否则报错
        Map<Integer, Transaction> collect = list.stream().collect(Collectors.toMap(Transaction::getYear, v -> v, (v, v2) -> v));
        System.out.println(collect);
    }


    @Test
    public void testT() {
        JSONArray jsonArr = JSON.parseArray("[[\"683\",\"0\"],[\"698\",\"1\"],[\"673\",\"2\"],[\"674\",\"3\"],[\"681\",\"4\"],[\"699\",\"5\"]]");
        JSONArray[] jsonArrays = jsonArr.toArray(new JSONArray[jsonArr.size()]);
        List<String> collect = Arrays.stream(jsonArrays).sorted(Comparator.comparingInt(a -> Integer.valueOf(a.getString(1)))).map(a -> a.get(0).toString()).collect(Collectors.toList());
        System.out.println(collect);
    }
}
