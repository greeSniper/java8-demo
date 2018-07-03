package com.tangzhe.java8.cxytd;

/**
 * 接口里能实现方法，并且能作为lambda表达式调用
 */
@FunctionalInterface
public interface Test {

    //只能有一个未实现的方法
    public abstract void apply();

    static void hello() {
        System.out.println("hello");
    }

    default void hi() {
        System.out.println("hi");
    }

}
