package com.clfops.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BasketItemRepository extends CrudRepository<BasketItem, Integer> {
	@Transactional
	@Modifying
	@Query("DELETE FROM BasketItem bi WHERE bi.basket = :basket") 
    void deleteBasketItemsByBasketId(@Param("basket") Basket basket);
	
	@Query("SELECT bi FROM BasketItem bi WHERE bi.basket = :basket") 
    List<BasketItem> getBasketItemsByBasketId(@Param("basket") Basket basket);
}

