package com.betacom.backend.dto;

import com.betacom.backend.model.WishlistItem;

public class WishlistItemDTO {

	private Long id;
	private ProductDTO product;
	
	public WishlistItemDTO() {
		super();
	}

	public WishlistItemDTO(Long id, ProductDTO product) {
		super();
		this.id = id;
		this.product = product;
	}

	public WishlistItemDTO(ProductDTO product) {
		super();
		this.product = product;
	}
	
	public WishlistItemDTO(WishlistItem wishlistItem) {
		this.id=wishlistItem.getId();
		this.product=new ProductDTO(wishlistItem.getProduct());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "WishlistItemDTO [id=" + id + ", product=" + product + "]";
	}
}
