package com.tangzhe.java8.cxytd;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.junit.Test;

/**
 * 新的时间API
 */
public class NewDateTest {

    @Test
    public void test01() {
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    /**
     * 时间差
     */
    @Test
    public void test02() {
        Instant start = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant end = Instant.now();

        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
    }

    /**
     * 类似于new Date()
     */
    @Test
    public void test03() {
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate: " + localDate);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime: " + localDateTime);
    }

    /**
     * 日期操作
     */
    @Test
    public void test04() {
        LocalDate date = LocalDate.of(2018, 11, 11);
        System.out.println(date);

        System.out.println("plus one year: " + date.plusYears(1));

        System.out.println("plus one day: " + date.plusDays(1));
        System.out.println("plus one day: " + date.plus(1, ChronoUnit.DAYS));

        System.out.println("获取时间的月份中的天数："+date.getDayOfMonth());
    }

    /**
     * 日期格式化
     */
    @Test
    public void test05() {
        LocalDate date = LocalDate.now();
        String val = date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println(val);

        Instant instant = new Date().toInstant();
        System.out.println(instant);
    }

}
