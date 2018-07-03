package com.tangzhe.java8.cxytd;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 反射方法参数的名称获取
 */
public class ParamterTest {

    public static void main(String[] args) {
        Method method;
        try {
            method = ParamterTest.class.getMethod("test", String.class);
            Parameter[] pas = method.getParameters();
            for (Parameter pa : pas) {
                System.out.println(pa.getName());
            }
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }

    }

    public void test(String name) {

    }

}
