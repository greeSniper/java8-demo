package com.tangzhe.java8.cxytd;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Lambda表达式
 */
public class LambdaTest {

    public static void test01() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是线程一");
            }
        }).start();

        new Thread(() -> System.out.println("我是线程二")).start();
    }

    public static void test02() {
        String[] names = "jason jack andy jay".split(" ");
        Arrays.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });
        System.out.println(Arrays.toString(names));

        Arrays.sort(names, (o1, o2) -> Integer.compare(o2.length(), o1.length()));
        System.out.println(Arrays.toString(names));
    }

    public static void test03() {
        List<String> list = Arrays.asList("1", "2", "3");
        for (String str : list) {
            System.out.println(str);
        }

        list.forEach(str -> System.out.println(str));

        list.forEach(System.out::println);
    }

    public static void main(String[] args) {
        test03();
    }

}
