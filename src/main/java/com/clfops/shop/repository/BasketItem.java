package com.clfops.shop.repository;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BasketItem {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "basket_id")
	private Basket basket;
	
	private String productName;
	private Integer count;
	private BigDecimal unitPrice;
	
	public BasketItem() {}
	public BasketItem(Basket basket, String productName, Integer count, BigDecimal unitPrice) {
		this.basket = basket;
		this.productName = productName;
		this.count = count;
		this.unitPrice = unitPrice;
	}

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
}
