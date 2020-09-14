package com.clfops.shop.repository;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Basket {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	@Column(unique=true)
	private Integer customerId;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "basket")
	private List<BasketItem> items;

	public Basket() {}
	public Basket(Integer customerId) {
		this.customerId = customerId;
	}
	public Basket(Integer customerId, List<BasketItem> items) {
		this(customerId);
		this.items = items;
	}
	
	public Integer getCustomerId() {
		return customerId;
	}
	
	public List<BasketItem> getItems() {
		return items;
	}
}
