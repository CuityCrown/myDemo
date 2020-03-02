package com.ryml.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ryml.entity.Menu;
import com.ryml.entity.Trader;
import com.ryml.entity.Transaction;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.lang.reflect.Field;
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
        System.out.println("collect");
    }

    @Test
    public void testParallelStream2() {
        Long start = System.currentTimeMillis();
        List<Trader> collect = list.stream().parallel().map(transaction -> {
            System.out.println(transaction + "===========");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return transaction.getTrader();
        }).collect(Collectors.toList());
        System.out.println(collect);
        Long end = System.currentTimeMillis();
        System.out.println(end - start);
        Long start2 = System.currentTimeMillis();
        List<Trader> collect2 = list.stream().map(transaction -> {
            System.out.println(transaction + "-------------");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return transaction.getTrader();
        }).collect(Collectors.toList());
        Long end2 = System.currentTimeMillis();
        System.out.println(collect2);
        System.out.println(end2 - start2);
    }

    @Test
    public void testMenu() {
        Menu menu1 = new Menu(1, "动物", 0, null);
        Menu menu2 = new Menu(2, "药品", 0, null);
        Menu menu3 = new Menu(3, "鸟类", 1, null);
        Menu menu4 = new Menu(4, "哺乳类", 1, null);
        Menu menu5 = new Menu(5, "中药", 2, null);
        Menu menu6 = new Menu(6, "西药", 2, null);
        Menu menu7 = new Menu(7, "孔雀", 3, null);
        Menu menu8 = new Menu(8, "熊猫", 4, null);
        Menu menu9 = new Menu(9, "枸杞", 5, null);
        Menu menu10 = new Menu(10, "肾宝片", 6, null);
        List<Menu> menus = Arrays.asList(menu1, menu2, menu3, menu4, menu5, menu6, menu7, menu8, menu9, menu10);
        Map<Integer, List<Menu>> collect = menus.stream().collect(Collectors.toMap(Menu::getId, (Menu menu) -> {
            ArrayList<Menu> objects = new ArrayList<>(menu.getChildrenMenus());
            return objects;
        }));
        System.out.println(collect);
    }

    public List<Menu> recursion(List<Menu> list, Map<Integer, List<Menu>> menuMap) {
        list.forEach(menu -> {
            List<Menu> menus = menuMap.get(menu.getId());
            if (menus != null) {
                menu.setChildrenMenus(menus);
                recursion(menus, menuMap);
            }
        });
        return list;
    }

    @Test
    public void test() {
        String a = "Aa";
        String b = "BB";
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
    }

    @Test
    public void test1() throws NoSuchFieldException, IllegalAccessException {
        Menu menu = new Menu();
        menu.setChildrenMenus(new ArrayList<>());
        menu.setId(1);
        test2(menu);
    }

    public void test2(Object obj) throws NoSuchFieldException, IllegalAccessException {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        System.out.println(declaredFields);

        Field id = obj.getClass().getDeclaredField("id");
        id.setAccessible(true);
        System.out.println(id.get(obj));
    }

    @Test
    public void test3() {
        String s = DigestUtils.md5DigestAsHex("123".getBytes());
        System.out.println(s);
    }

    @Test
    public void copy() {
        Menu menu1 = new Menu(1, "动物", 0, null);
        List<Menu> list = new ArrayList<>();
        list.add(menu1);
        List<Menu> collect = list.stream().filter(Objects::nonNull).collect(Collectors.toList());
        for (Menu menu : collect) {
            menu.setId(2);
        }
        System.out.println(JSONObject.toJSONString(list));
        System.out.println(JSONObject.toJSONString(collect));
    }


    @Test
    public void test8() {
        List<Person> list = new ArrayList();
        list.add(new Person("1001", "小A"));
        list.add(new Person("1002", "小B"));
        list.add(new Person("1003", null));
        list.stream().collect(Collectors.toMap(Person::getId,Person::getName));
        System.out.println(map);
    }

}
