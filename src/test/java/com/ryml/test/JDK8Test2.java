package com.ryml.test;

import com.ryml.entity.Trader;
import com.ryml.entity.Transaction;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/9/20 11:23
 */
public class JDK8Test2 {

    private static List<Transaction> list;

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

}