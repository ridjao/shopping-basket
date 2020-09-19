package com.clfops.shop.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Basket {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(unique=true)
	private Integer customerId;

	public Basket() {}
	public Basket(Integer customerId) {
		this.customerId = customerId;
	}
	
	public Integer getCustomerId() {
		return customerId;
	}
}
