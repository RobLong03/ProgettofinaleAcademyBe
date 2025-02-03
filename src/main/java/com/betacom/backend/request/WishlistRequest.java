package com.betacom.backend.request;

public class WishlistRequest {

	private Long id;
	private Long customerId;
	
	public WishlistRequest() {
		super();
	}

	public WishlistRequest(Long id, Long customerId) {
		super();
		this.id = id;
		this.customerId = customerId;
	}

	public WishlistRequest(Long customerId) {
		super();
		this.customerId = customerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "WishlistRequest [id=" + id + ", customerId=" + customerId + "]";
	}
}
