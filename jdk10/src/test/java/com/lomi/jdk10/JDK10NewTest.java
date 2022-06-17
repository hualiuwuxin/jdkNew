package com.lomi.jdk10;

import org.junit.Test;

import java.awt.print.Book;
import java.util.*;
import java.util.function.Supplier;

/**
 *
 * jdk10 新特性
 * @author ZHANGYUKUN
 * @date 2022/6/17
 */
public class JDK10NewTest {


    /**
     * 1 局部变量类型推断（通过右边推断，右边必须是明确的类型）
     * 2 var 不是关键字，而是一个特殊的类型（ 或者把它当做一个特殊的泛型 ）
     * 3 var 编译以后会变成明确的类型
     */
    @Test
    public void test1(){
        // var 不是关键字，而是一个特殊的类型
        var var = "var";


        var a  = 1L;
        System.out.println(  a );

        //修改别的类型是不允许的，但是可以自动转化的类型是可以
        //a = ""；
        a = 2;


        //var 右边不是lamdda 表达式。lamdda 表达式是通过左边的接口函数的类型推断出来的。是不确定的类型
        Supplier f =  ()->{ return 1; };
        //var f2 =  ()->{ return 1; };

        //右边的泛型参数如果不指定就是Object
        Collection<String> colle1 = Set.of("1","2","3");
        var colle2 = new HashSet<>();

        //省略类型的静态数组不能指向 var(我个人觉得这个应该允许，右边的是明确的值)
        String[] array0 = new String[]{"1","2","3"};
        String[] array1 = {"1","2","3"};
        Object[] array11 = {1,"2", Long.parseLong("1" ) };
        //var array2 = {"1","2","3"};

        //var 不能指向空值，因为空值是不明确的类型
        Integer int1 = null;
       // var int2 = null;


        //var 定义返回值类型，和形参类型,返回值和形参本来就是用来约束类型的。


        //var 不能是 成员变量，因为成员变量有默认值。var 不是明确的类型，不能知道默认值，
        // 而且成员变量应该是明确的类型，var 的 使用场景是类似于 循环遍历之类的类型明确 ，使用者毫不关心，不需要手动声明的场景


        //var的正确使用姿势（我们其实不关心内循环遍量的类型，因为它只能是那个确定类型，相当于 idea的 new Object().var+回车 自动推断变量类型）
        Map<String, String> map = Map.ofEntries(Map.entry("key1", "value1"), Map.entry("key2", "valu2"));
        for(var entry : map.entrySet()){
            System.out.println(  entry.getKey() );
            System.out.println(  entry.getValue() );
        }

    }



    /**
     *
     *   Optional 添加 orElseThrow 方法， 取不到 直接 抛出异常，而不是在在使用的时候才抛出异常
     *
     */
    @Test
    public void test2(){

        var optional =   Optional.of("");
        optional.orElseThrow();


    }






}
