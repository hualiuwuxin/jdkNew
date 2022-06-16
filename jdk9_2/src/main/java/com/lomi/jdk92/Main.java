package com.lomi.jdk92;

import com.lomi.jdk91.Goods;

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
