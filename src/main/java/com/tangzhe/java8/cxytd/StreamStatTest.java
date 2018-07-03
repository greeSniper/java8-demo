package com.tangzhe.java8.cxytd;

import lombok.Data;
import org.junit.Test;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream流式操作集合
 */
public class StreamStatTest {

    /**
     * 最大值、最小值
     */
    @Test
    public void test01() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        Optional<Integer> optional = list.stream().max(Integer::compareTo);
        System.out.println("max: " + optional.get());

        System.out.println("min: " + list.stream().min(Integer::compareTo).get());;
    }

    /**
     * 统计操作
     */
    @Test
    public void test02() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        IntSummaryStatistics stat = list.stream()
                .collect(Collectors.summarizingInt(Integer::new));
        System.out.println(stat.getMax());
        System.out.println(stat.getMin());
        System.out.println(stat.getCount());
        System.out.println(stat.getSum());
        System.out.println(stat.getAverage());
    }

    /**
     * 聚合操作
     */
    @Test
    public void test03() {
        Stream<City> cityStream = Stream.of(
                new City("上海", 100),
                new City("北京", 100),
                new City("上海", 100)
        );

        Map<String, Long> map = cityStream.collect(
                Collectors.groupingBy(
                        City::getName,
                        Collectors.summingLong(City::getCount)
                )
        );

        map.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    /**
     * 上海: IntSummaryStatistics{count=2, sum=200, min=100, average=100.000000, max=100}
     * 北京: IntSummaryStatistics{count=1, sum=100, min=100, average=100.000000, max=100}
     */
    @Test
    public void test04() {
        Stream<City> cityStream = Stream.of(
                new City("上海", 100),
                new City("北京", 100),
                new City("上海", 100)
        );

        Map<String, IntSummaryStatistics> map = cityStream.collect(
                Collectors.groupingBy(
                        City::getName,
                        Collectors.summarizingInt(City::getCount)
                )
        );

        map.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    /**
     * flatMap
     */
    @Test
    public void test05() {
        Stream.of("java", "js", "php", "java", "go")
                .collect(Collectors.groupingBy(String::new, Collectors.counting()))
			    .forEach((k, v) -> System.out.println(k + "\t" + v));

        Stream.of(Arrays.asList("a", "b"), Arrays.asList("b", "c")).flatMap(x -> x.stream()).distinct().forEach(System.out::println);
    }

    /**
     * reduce
     */
    @Test
    public void test06() {
        System.out.println(Stream.of(1, 2, 3, 4, 5).reduce((a, b) -> a + b).get());
    }

}

@Data
class City {
    private String name;
    private Integer count;

    public City() {}
    public City(String name, Integer count) {
        this.name = name;
        this.count = count;
    }
}