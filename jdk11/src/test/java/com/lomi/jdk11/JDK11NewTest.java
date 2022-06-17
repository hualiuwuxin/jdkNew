package com.lomi.jdk11;

import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author ZHANGYUKUN
 * @date 2022/6/17
 */
public class JDK11NewTest {

    /**
     * 局部变量类型推断可以注解？
     */
    @Test
    public <s> void test1(){

        /*Consumer<String> supplier = ( @Deprecated s )->{

        };*/



    }

    /**
     * String 的几个新方法
     * trim()可以去除字符串前后的半角空白字符  所以最好用这个，
     * strip()可以去除字符串前后的全角和半角空白字符
     */
    @Test
    public <s> void test2(){

        //aaa前面的空格第一个是半角，第二个是全角，全角也会认为是空白字符
        System.out.println("strip:"+ " 　aaa 　".strip() );

        //aaa前面的空格第一个是半角，第二个是全角, 全角，不会被认为是空白字符
        System.out.println("trim:"+  " 　aaa 　".trim() );

        //去前面的空格(可以去掉全角)
        System.out.println("stripLeading:"+  " 　aaa 　".stripLeading() );

        //去后面的空格(可以去掉全角)
        System.out.println("stripTrailing:"+  " 　aaa 　".stripTrailing() );

        //判断是否空白字符(全角也认为是空白字符)
        System.out.println("isBlank:"+  " 　".isBlank() );

        //重复多少次
        System.out.println("repeat:"+  "zyk".repeat(2) );


    }

    /**
     * Optional 添加 isEmpty(),等于 !Optional.ofNullable(null).isPresent() ，估计是觉得 isPresent 没有 isEmpty 直观
     */
    @Test
    public <s> void test3(){
        System.out.println( Optional.ofNullable(null).isEmpty() );
        System.out.println( !Optional.ofNullable(null).isPresent() );


        System.out.println( Optional.ofNullable( new ArrayList<String>() ).isEmpty() );
        System.out.println( !Optional.ofNullable( new ArrayList<String>() ).isPresent() );
    }




}
