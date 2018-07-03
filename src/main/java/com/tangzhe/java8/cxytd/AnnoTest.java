package com.tangzhe.java8.cxytd;

import java.lang.annotation.*;
import java.util.stream.Stream;

/**
 * 可重复的注解
 */
public class AnnoTest {

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Tangzhes {
        Tangzhe[] value();
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(Tangzhes.class)
    public @interface Tangzhe {
        String value();
    }

    @Tangzhe("tangzhe1")
    @Tangzhe("tangzhe2")
    public interface Service {

    }

    public static void main(String[] args) {
        Tangzhe[] tangzhes = Service.class.getAnnotationsByType(Tangzhe.class);
        Stream.of(tangzhes).forEach(System.out::println);

        for(Tangzhe tangzhe : tangzhes) {
            System.out.println(tangzhe.value());
        }
    }

}
