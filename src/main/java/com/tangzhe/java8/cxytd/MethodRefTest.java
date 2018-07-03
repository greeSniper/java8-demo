package com.tangzhe.java8.cxytd;

import java.util.Arrays;
import java.util.List;

/**
 * 方法、构造器引用
 */
public class MethodRefTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("张三", "李四", "王五");
        list.forEach(System.out::println);
        System.out.println("------------------------------------");

        list.stream().map(String::new).forEach(System.out::println);
        System.out.println("------------------------------------");

        List<Object> list2 = Arrays.asList("a", 1, "b", "c");
        //转为字符串并输出
        list2.stream().map(String::valueOf).forEach(System.out::println);
        System.out.println("------------------------------------");

        Test test = new Test() {
            @Override
            public void apply() {
                System.out.println("apply");
            }
        };
        Test.hello();
        test.hi();
        test.apply();
    }

}
