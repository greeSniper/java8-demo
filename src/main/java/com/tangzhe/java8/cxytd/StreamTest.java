package com.tangzhe.java8.cxytd;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

/**
 * Stream流式操作集合
 */
public class StreamTest {

    /**
     * 过滤
     */
    @Test
    public void test01() {
        List<String> names = Arrays.asList("jason", "andy", "jay");
        int count = 0;
        for (String name : names) {
            if(name.length() > 3) {
                count ++;
            }
        }
        System.out.println("> 3的数量：" + count);

        System.out.println("> 3的数量：" + names.stream().filter(x -> x.length()>3).count());
    }

    /**
     * map
     */
    @Test
    public void test02() {
        Stream.of("jason", "andy", "jay").forEach(System.out::println);

        Stream.of("jason", "andy", "jay").map(x -> x.toUpperCase()).forEach(System.out::println);

        Stream.of("jason", "andy", "jay").filter(x -> x.length() > 3).map(x -> x.toUpperCase()).forEach(System.out::println);

        Stream.of("jason", "andy", "jay").filter(x -> x.length() > 3).map(x -> x.toUpperCase()).limit(1).forEach(System.out::println);
    }

    /**
     * stream转list
     */
    @Test
    public void test03() {
        List<String> list = Stream.of("jason", "andy", "jay").collect(Collectors.toList());
    }

    /**
     * 去重
     */
    @Test
    public void test04() {
        System.out.println(Stream.of("jason", "andy", "jay").distinct().count());
    }

    /**
     * 变大写、过滤大于3、排序、输出
     */
    @Test
    public void test05() {
        Stream.of("jason", "andy", "jay")
                .map(String::toUpperCase)
                .filter(x -> x.length() > 3)
                .sorted((o1, o2) -> Integer.compare(o1.length(), o2.length()))
                .forEach(System.out::println);
    }

}
