package com.lomi.jdkTwo1;

import com.lomi.jdk9One1.Goods;

/**
 * @author ZHANGYUKUN
 * @date 2022/6/16
 */
public class Main {

    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.getGoodsList().add( new Goods() );

        System.out.println(  shoppingCart );
    }
}
