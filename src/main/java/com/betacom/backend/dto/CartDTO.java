package com.betacom.backend.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.betacom.backend.model.Cart;

public class CartDTO {

	private Long id;
	private List<CartItemDTO> items;
	private Double totalPrice;
	private CustomerDTO customer;

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

	public CartDTO(Long id, List<CartItemDTO> items, Double totalPrice,CustomerDTO customer) {
		super();
		this.id = id;
		this.items = items;
		this.totalPrice = totalPrice;
		this.customer = customer;
	}
	public CartDTO() {
		super();
	}
	public CartDTO(List<CartItemDTO> items, Double totalPrice,CustomerDTO customer) {
		super();
		this.items = items;
		this.totalPrice = totalPrice;
		this.customer = customer;
	}
	public CartDTO(Cart cart) {
		this.items = cart.getItems().stream()
	            .map(c -> new CartItemDTO(
	                    c.getProduct().getId(),
	                    c.getCart().getId(),
	                    c.getQuantity(),
	                    c.getPrice()
	            		))
	            .collect(Collectors.toList());
		this.customer = new CustomerDTO(cart.getCustomer());
		this.totalPrice = cart.getTotalPrice();
	}
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
	@Override
	public String toString() {
		return "CartDTO [id=" + id + ", items=" + items + ", totalPrice=" + totalPrice + "]";
	}
}
