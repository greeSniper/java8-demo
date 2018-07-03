package com.tangzhe.java8.cxytd;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Base64的支持
 */
public class Base64Test {

    public static void main(String[] args) {
        String username = "唐哲";
        //Base64编码
        String encodeStr = Base64.getEncoder().encodeToString(username.getBytes());
        System.out.println(encodeStr);
        //Base64解码
        String decodeStr = new String(Base64.getDecoder().decode(encodeStr.getBytes()));
        System.out.println(decodeStr);

        username = "张三";
        encodeStr = Base64.getEncoder().encodeToString(username.getBytes(StandardCharsets.UTF_8));
        System.out.println(encodeStr);
        decodeStr = new String(Base64.getDecoder().decode(encodeStr.getBytes()));
        System.out.println(decodeStr);
    }

}
