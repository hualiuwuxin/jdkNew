package com.lomi.jdk92;

import com.lomi.jdk91.Goods;
import com.lomi.jdk92.ShoppingCart;
import org.junit.Test;

/**
 * @author ZHANGYUKUN
 * @date 2022/6/16
 */
public class JDKTest1 {

    @Test
    public void test1(){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.getGoodsList().add( new Goods() );

        System.out.println(  shoppingCart );


    }


}
