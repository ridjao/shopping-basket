package com.clfops.shop.dto;

import java.math.BigDecimal;

public class Item {
	private Integer id;
	private String productName;
	private Integer count;
	private BigDecimal unitPrice;

	public Integer getId() {
		return id;
	}
	
	public String getProductName() {
		return productName;
	}

	public Integer getCount() {
		return count;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	
	public Item() {}
	public Item(Integer id, String productName, Integer count, BigDecimal unitPrice) {
		this.id = id;
		this.productName = productName;
		this.count = count;
		this.unitPrice = unitPrice;
	}
}
