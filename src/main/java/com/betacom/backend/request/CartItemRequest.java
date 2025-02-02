package com.betacom.backend.request;

public class CartItemRequest {

	private Long id;
	private Long productId;
	private Long cartId;
	private Integer quantity;
	private Double price;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getCartId() {
		return cartId;
	}
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public CartItemRequest(Long id, Long productId, Long cartId, Integer quantity, Double price) {
		super();
		this.id = id;
		this.productId = productId;
		this.cartId = cartId;
		this.quantity = quantity;
		this.price = price;
	}
	public CartItemRequest() {
		super();
	}

	public CartItemRequest(Long productId, Long cartId, Integer quantity, Double price) {
		super();
		this.productId = productId;
		this.cartId = cartId;
		this.quantity = quantity;
		this.price = price;
	}
}
