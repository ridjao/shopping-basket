package com.clfops.shop.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Integer> {
	@Query("SELECT b FROM Basket b WHERE b.customerId = :customerId") 
    Basket getBasketByCustomerId(@Param("customerId") Integer customerId);
}

