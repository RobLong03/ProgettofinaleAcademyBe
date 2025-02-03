package com.betacom.backend.request;

public class WishlistItemRequest {

	private Long id;
	private Long productId;
	
	public WishlistItemRequest() {
		super();
	}
	
	public WishlistItemRequest(Long id, Long productId) {
		super();
		this.id = id;
		this.productId = productId;
	}

	public WishlistItemRequest(Long productId) {
		super();
		this.productId = productId;
	}

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

	@Override
	public String toString() {
		return "WishlistItemRequest [id=" + id + ", productId=" + productId + "]";
	}
}
