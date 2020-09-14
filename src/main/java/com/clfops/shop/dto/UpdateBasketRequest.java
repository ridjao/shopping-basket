package com.clfops.shop.dto;

import java.util.List;

public class UpdateBasketRequest {
	private List<Item> addItems;
	private List<Integer> removeItemIds;
	
	public List<Item> getAddItems() {
		return addItems;
	}
	
	public List<Integer> getRemoveItemIds() {
		return removeItemIds;
	}
}
