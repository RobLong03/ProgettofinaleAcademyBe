package com.betacom.backend.request.cart;

import java.util.List;

import com.betacom.backend.dto.cart.CartItemDTO;

public class CartRequest {

	private Long id;
	private List<CartItemDTO> items;
	private Double totalPrice;
	private Double customerId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<CartItemDTO> getItems() {
		return items;
	}
	public void setItems(List<CartItemDTO> items) {
		this.items = items;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Double customerId) {
		this.customerId = customerId;
	}

	public CartRequest(Long id, List<CartItemDTO> items, Double totalPrice,Double customerId) {
		super();
		this.id = id;
		this.items = items;
		this.totalPrice = totalPrice;
		this.customerId = customerId;
	}
	public CartRequest() {
		super();
	}

	public CartRequest(List<CartItemDTO> items, Double totalPrice,Double customerId) {
		super();
		this.items = items;
		this.totalPrice = totalPrice;
		this.customerId = customerId;
	}
}
