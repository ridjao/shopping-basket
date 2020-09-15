package com.clfops.shop;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.clfops.shop.dto.CreateBasketRequest;
import com.clfops.shop.dto.Item;
import com.clfops.shop.dto.ShoppingBasketResponse;
import com.clfops.shop.dto.UpdateBasketRequest;
import com.clfops.shop.repository.Basket;
import com.clfops.shop.repository.BasketItem;
import com.clfops.shop.repository.BasketItemRepository;
import com.clfops.shop.repository.BasketRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="/api/v1/baskets")
public class ShoppingBasketController {
	private static final Logger logger = LoggerFactory.getLogger(ShoppingBasketController.class);
	
	@Autowired 
	private BasketRepository basketRepository;
	
	@Autowired 
	private BasketItemRepository basketItemRepository;
	
	@PutMapping(path="/{customerId}",
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ShoppingBasketResponse createBasket(@PathVariable("customerId") Integer customerId,
			@RequestBody(required=false) CreateBasketRequest request) {
		Basket basket = basketRepository.getBasketByCustomerId(customerId);
		if (basket == null) {
			basket = new Basket(customerId);	
			basketRepository.save(basket);
			logger.debug("Adding new basket for customer {}", customerId);
		}
		else {
			basketItemRepository.deleteBasketItemsByBasketId(basket);
			logger.debug("Replacing existing basket for customer {}", customerId);
		}
		
		if (request == null) {
			ShoppingBasketResponse response = new ShoppingBasketResponse("OK");
			return response;
		}
		
		List<Item> items = request.getItems();
		if (items != null) {
			for (Item item : items) {
				basketItemRepository.save(
						new BasketItem(basket, item.getProductName(), item.getCount(), item.getUnitPrice()));
			}
		}
	
		ShoppingBasketResponse response = new ShoppingBasketResponse("OK");
		response.setItems(getCurrentBasketItems(basket));
		return response;
	}
	
	@PostMapping(path="/{customerId}/items",
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ShoppingBasketResponse updateBasket(@PathVariable("customerId") Integer customerId,
			@RequestBody UpdateBasketRequest request) {
		Basket basket = basketRepository.getBasketByCustomerId(customerId);
		if (basket == null) {
			return new ShoppingBasketResponse("Customer basket does not exist");
		}
		
		List<Integer> basketItemIds = request.getRemoveItemIds();
		if (basketItemIds != null) {
			for (Integer basketItemId : basketItemIds) {
				try {
					basketItemRepository.deleteById(basketItemId);
				} catch (EmptyResultDataAccessException e) {
					logger.info("Cannot remove item {}, it's not in the customer basket", basketItemId);
				}
			}
		}

		List<Item> items = request.getAddItems();
		if (items != null) {
			for (Item item : items) {
				basketItemRepository.save(
						new BasketItem(basket, item.getProductName(), item.getCount(), item.getUnitPrice()));
			}
		}
		
		ShoppingBasketResponse response = new ShoppingBasketResponse("OK");
		response.setItems(getCurrentBasketItems(basket));
		return response;
	}
	
	@GetMapping(path="/{customerId}/items",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ShoppingBasketResponse getBasketItems(@PathVariable("customerId") Integer customerId) {
		Basket basket = basketRepository.getBasketByCustomerId(customerId);
		if (basket == null) {
			return new ShoppingBasketResponse("Customer basket does not exist");
		}
		
		ShoppingBasketResponse response = new ShoppingBasketResponse("OK");
		response.setItems(getCurrentBasketItems(basket));
		return response;
	}
	
	private List<Item> getCurrentBasketItems(Basket basket) {
		List<Item> currentItems = new ArrayList<>();
		for (BasketItem basketItem : basketItemRepository.getBasketItemsByBasketId(basket)) {
			currentItems.add(new Item(basketItem.getId(), basketItem.getProductName(),
					basketItem.getCount(), basketItem.getUnitPrice()));
		}
		return currentItems;
	}
}
