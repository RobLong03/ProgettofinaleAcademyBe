package com.betacom.backend.request.cart;

public class CartRequest {

	private Long id;
	private Double totalPrice;
	private Long customerId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public CartRequest(Long id, Double totalPrice,Long customerId) {
		super();
		this.id = id;
		this.totalPrice = totalPrice;
		this.customerId = customerId;
	}
	public CartRequest() {
		super();
	}


	public CartRequest(Double totalPrice, Long customerId) {
		super();
		this.totalPrice = totalPrice;
		this.customerId = customerId;
	}
	@Override
	public String toString() {
		return "CartRequest [id=" + id + ", totalPrice=" + totalPrice + ", customerId=" + customerId + "]";
	}

}
