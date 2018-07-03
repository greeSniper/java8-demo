package com.tangzhe.java8.cxytd;

import java.util.Optional;

/**
 * Created by 唐哲
 * 2018-03-29 13:17
 * optional避免空指针
 */
public class OptionalTest {

    private String name;

    public String getName() {
        return Optional.ofNullable(this.name).orElse("default");
    }

    public static void main(String[] args) {
        OptionalTest test = new OptionalTest();
        System.out.println(test.getName());

        //String name = null;
        String name = "test";
        if (name!=null && name.equals("test")) {
            System.out.println(name.toUpperCase());
        }
        if (Optional.ofNullable(name).filter(x -> x.equals("test")).isPresent()) {
            System.out.println(name.toUpperCase());
        }
    }

}
