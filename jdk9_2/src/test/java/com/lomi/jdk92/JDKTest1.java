package com.lomi.jdk92;

import com.lomi.jdk91.Goods;
import com.lomi.jdk92.ShoppingCart;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author ZHANGYUKUN
 * @date 2022/6/16
 */
public class JDKTest1 {


    /**
     * 使用模块
     */
    @Test
    public void test1(){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.getGoodsList().add( new Goods() );

        System.out.println(  shoppingCart );


    }

    /**
     * 匿名内部类前面使用<>不会报错，之前java8会报错
     */
    @Test
    public void test2(){

        //java8 只能这样写
        Comparator<Object> comparator2 = new Comparator<Object>() {

            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };

        //java8 这样写会报错
        Comparator<Object> comparator = new Comparator<>() {

            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };
    }



    /**
     *  对try 自动关闭资源的优化
     */
    @Test
    public void test3() throws IOException {
        //jdk8以前
        FileWriter fw = null;
        try{
            fw = new FileWriter("C:\\Users\\ZHANGYUKUN\\Desktop\\a.txt");
            fw.write("aaaa");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if( fw != null ){
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        //jdk8,要求资源的申明和赋值都必须在 try的括号内
        try (
                FileWriter fw2 = new FileWriter("C:\\Users\\ZHANGYUKUN\\Desktop\\b.txt");
                FileWriter fw3 = new FileWriter("C:\\Users\\ZHANGYUKUN\\Desktop\\b.txt");
        ){

            fw2.write("bbbb");
            fw3.write("cccc");
        }catch (Exception e){
            e.printStackTrace();
        }

        //jdk9,允许关闭的资源在外面申明并且赋值（不允许在外面申明，try（）内赋值）
        FileWriter fw5 = new FileWriter("C:\\Users\\ZHANGYUKUN\\Desktop\\b.txt");
        try (
                FileWriter fw4 = new FileWriter("C:\\Users\\ZHANGYUKUN\\Desktop\\b.txt");
                fw5
                ){
            fw4.write("eeee");
            fw5.write("ffff");
        }catch (Exception e){
            e.printStackTrace();
        }


        //不管那种try()的优化实际上在 try 方法体内部都使用的一个静态指针（这个资源在 try{} 里面是不能修改的，相当于一个常量），并且这个静态指针作用范围只有 try 结束就自动释放了

        //自动关闭的资源需要实现 AutoCloseable 接口


        System.out.println("end---------");
    }



    /**
     *  String 类型 以前使用char 数组，现在用的 byte数组（可以省空间）（StringBuilder ，StringBuffer 也是换成byte数组了，正常我们应该用StringBuilder，而不是StringBuffer）
     */
    @Test
    public void test4() throws IOException {

    }


    /**
     *  只读集合的工厂方法
     */
    @Test
    public void test5() throws IOException {
        //这个方式创建的定长集合，依旧可以修改
        List<Integer> readOnlyList = Arrays.asList(1, 2, 3,null);
        readOnlyList.set(0,100);
        System.out.println( readOnlyList.get(0) );


        //以前创建只读集合
        Map<String, String> readOnlyMap = Collections.unmodifiableMap(new HashMap<String, String>());



        //jdk9写法（List.Set,Map都有类似的方法），为啥作者要把同样的方法写10 次？
        Set<Integer> set = Set.of(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        Map.ofEntries( Map.entry("key","value") );

    }

    /**
     *  直接把 输入流 对接到输出流（自带8K缓冲区），怎么获取路径？
     */
    @Test
    public void test6() throws IOException {

        try(
                InputStream inputStream = JDKTest1.class.getClassLoader().getResourceAsStream("a.txt");
                FileOutputStream outputStream = new FileOutputStream( "b.txt"  );
        ){

           inputStream.transferTo(outputStream);

        }catch (Exception e){

        }

    }


    /**
     * 新的 stream api   Zzhangyu123
     */
    @Test
    public void streamApi() throws IOException {

        //takeWhile（第一个不满足条件一户的都会删除）
        System.out.println( Stream.of(1, 2, 3, 4, 5, 6, 7, 8).takeWhile(item -> (item < 3)).toArray().length );
        System.out.println("---------------------------------------------------------------");
        //dropWhile（第一个不满足条件之前的都会删除）
        System.out.println( Stream.of(1, 2, 3, 4, 5, 6, 7, 8).dropWhile(item -> (item < 3)).toArray().length );

        System.out.println("---------------------------------------------------------------");

        //以前的Stream.of(null)，是不允许的。jdk9 Stream.ofNullable(null) 允许空
        Stream.ofNullable(null);
        Stream.of(null,null);
        //Stream.of(null);

        //iterate 自带退出条件
        Stream.iterate( 0,item->item<100,item->item+1 ).forEach(System.out::println);

    }


}
