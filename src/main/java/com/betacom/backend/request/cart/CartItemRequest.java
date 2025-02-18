package com.betacom.backend.request.cart;

public class CartItemRequest {

	private Long id;
	private Long productId;
	private Long cartId;
	private Integer quantity;

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

	public CartItemRequest(Long id, Long productId, Long cartId, Integer quantity) {
		super();
		this.id = id;
		this.productId = productId;
		this.cartId = cartId;
		this.quantity = quantity;
	}
	public CartItemRequest() {
		super();
	}

	public CartItemRequest(Long productId, Long cartId, Integer quantity) {
		super();
		this.productId = productId;
		this.cartId = cartId;
		this.quantity = quantity;
	}
}
