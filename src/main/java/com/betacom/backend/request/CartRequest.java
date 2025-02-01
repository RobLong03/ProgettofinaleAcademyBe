package com.betacom.backend.request;

import java.util.List;

import com.betacom.backend.dto.CartItemDTO;

public class CartRequest {

	private Long id;
	private List<CartItemDTO> items;
	private Double totalPrice;
	
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
	public CartRequest(Long id, List<CartItemDTO> items, Double totalPrice) {
		super();
		this.id = id;
		this.items = items;
		this.totalPrice = totalPrice;
	}
	public CartRequest() {
		super();
	}
	public CartRequest(List<CartItemDTO> items, Double totalPrice) {
		super();
		this.items = items;
		this.totalPrice = totalPrice;
	}
}
