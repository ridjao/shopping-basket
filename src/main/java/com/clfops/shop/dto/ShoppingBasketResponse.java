package com.clfops.shop.dto;

import java.util.List;

public class ShoppingBasketResponse {
	private String status;
	private List<Item> items;
	
	public ShoppingBasketResponse(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
