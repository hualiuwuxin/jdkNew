package com.lomi.jdkTwo2;

import com.lomi.jdk9One1.Goods;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart2 {
	
	private List<Goods> goodsList = new ArrayList<>();


	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}
}
