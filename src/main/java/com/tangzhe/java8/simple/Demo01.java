package com.tangzhe.java8.simple;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by 唐哲
 * 2018-03-30 16:51
 */
public class Demo01 {

    /**
     * 允许在接口中有默认方法实现
     */
    @Test
    public void test01() {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };

        formula.calculate(100);
        formula.sqrt(16);
    }

    @Test
    public void test02() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        names.forEach(System.out::println);
        System.out.println("-----------------------------");

        Collections.sort(names, (o1, o2) -> Integer.compare(o1.length(), o2.length()));
        names.forEach(System.out::println);
    }

    @Test
    public void test03() {
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        System.out.println(converter.convert("123"));
    }

    @Test
    public void test04() {
        Converter<String, Integer> converter = Integer::valueOf;
        System.out.println(converter.convert("123"));
    }

    @Test
    public void test05() {
        Something something = new Something();
        Converter<String, String> converter = something::startsWith;
        String result = converter.convert("hello");
        System.out.println(result);
    }

}

interface Formula {
    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}

@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}

class Something {
    public String startsWith(String str) {
        return String.valueOf(str.charAt(0));
    }
}