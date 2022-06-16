package com.lomi.jdk92;

import com.lomi.jdk91.Goods;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
	
	private List<Goods> goodsList = new ArrayList<>();


	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}
}
