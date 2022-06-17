package com.lomi.jdkTwo1;

import com.lomi.jdk9One1.Goods;
import com.lomi.jdk9One2.Book;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author ZHANGYUKUN
 * @date 2022/6/16
 */
public class JDK9NewTest1 {


    /**
     * 使用模块,相当于在package 上面 多了一层，如果在classpath 根目录有了 module-info.java 文件，那么引用的jar包就不是全部使用，而需要在 module-info.java 里面声明使用
     */
    @Test
    public void test1(){
        //Goods的包被暴露出来可以倍使用（如果不导入模块，就使用，是不能编译通过，除非当前工程不使用模块化管理）
        //模块化管理是相互的，需要 引用方和被引用方都 使用模块化管理才有效
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.getGoodsList().add( new Goods() );


        //Book的包没有被暴露出来，不能使用（但是目前版本的编译并不会保存，估计后面版本会优化），下面的代码允许会抛出异常
        Book book = new Book();

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
     *  对try 自动关闭资源的优化，可以使用try外部定义的AutoCloseable资源
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
        //char 是2 个字节，byte 是一个字节，jvm 内部的 编码是 UTF-16或者是定长2字节的UES2，现在才是动长的 UTF-16(短的用1个字节，长的用2个字节，2个字节转不下的用4个字节)
        //这样做的原因是大多数时候程序的字符串都是 ASCII 编码可以表示的(一个字节就够了)，全都用2个字节太浪费内存

    }


    /**
     *  只读集合的工厂方法
     *  三大集合 提供了of方法，没有jdk8 允许了接口的静态方法，所以现在 of的工厂方法都由 三大集合接口提供，而不是 Collections 提供
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
     *  输入流，提供 transferTo 方法
     */
    @Test
    public void test6() throws IOException {

        try(
                InputStream inputStream = JDK9NewTest1.class.getClassLoader().getResourceAsStream("a.txt");
                FileOutputStream outputStream = new FileOutputStream( "b.txt"  );
        ){

           inputStream.transferTo(outputStream);

        }catch (Exception e){

        }

    }


    /**
     * stream api 新增了4个方法takeWhile,dropWhile,ofNullable,自带退出条件的iterate
     */
    @Test
    public void test7() throws IOException {

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


    /**
     *  Optional 的一些新方法 ifPresentOrElse()，or()，stream()
     */
    @Test
    public void test8() throws IOException {

        //Optional.or 空值的时候返回 一个默认的 Optional
        String value = null;
        Optional optional =  Optional.ofNullable( value );
        optional = optional.or( ()-> Optional.of("默认值")  );
       // System.out.println("结果：" + optional.get() );


        //不为空调用方法A，为空调用方法2（ 和  optional.orElseThrow() 类似 ）
        optional.ifPresentOrElse(System.out::println,()->{
            System.out.println("是空");
        });






        //jdk8有的方法，和 orElse 的加强版
        optional.orElseGet( ()->"默认值" );


        //平铺的optional（其实也没有平铺，重点在map Optional ）
        List<String> list = null;
        Optional<String> s = Optional.ofNullable(list).flatMap(item -> Optional.of(item.get(0)));

    }

    /**
     * 私有方法
     */
    @Test
    public void test9(){
        Impl1 impl1 = new Impl1();
        Impl2 impl2 = new Impl2();

        impl1.methodDefault();
        impl1.methodNotmal();

        //接口的静态方法只能通过接口名字调用，不能通过实例对象调用
        Interface1.methodStatic();

        //私有方法只能自己调用
        //impl1.methodProvide();



    }


    /**
     *  nashorn 引擎
     */
    @Test
    public void test10() throws IOException {

        //后面的jdk版本删除了，没继续了解
    }


}
