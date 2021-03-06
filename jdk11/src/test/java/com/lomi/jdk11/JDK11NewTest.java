package com.lomi.jdk11;

import org.junit.Test;
import org.junit.validator.AnnotationValidator;
import org.junit.validator.ValidateWith;

import javax.xml.transform.stax.StAXSource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

        //这种一直都可以
        @Deprecated
        Long a = 1L;

        //在jdk10 上是不能这样用的，用注解直接在var 标注的局部变量上加验证
        Consumer<String> supplier = ( @ValidateWith(AnnotationValidator.class) var s )->{

        };



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


    /**
     *  内置 HttpClient  代替  HttpURLConnecttion
     */
    @Test
    public <s> void test4() throws URISyntaxException, IOException, InterruptedException {
        //比 HttpURLConnecttion 简单和很多，但是 我重来没用过 HttpURLConnecttion，以前也是用的三方的HttpClient
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(new URI("https://www.baidu.com/")).build();
        HttpResponse.BodyHandler<String> stringBodyHandler = HttpResponse.BodyHandlers.ofString();
        String  stringBody = httpClient.send(httpRequest,stringBodyHandler).body();
        System.out.println( stringBody );

        // HttpURLConnecttion 需要放回结果 是一个输入流，需要手动的读这个流然后再包装成Strin
    }



}
