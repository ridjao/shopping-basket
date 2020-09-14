package com.clfops.shop.dto;

import java.util.List;

public class CreateBasketRequest {
	private List<Item> items;
	public List<Item> getItems() {
		return items;
	}
}
