package com.betacom.backend.dto.wishlist;

import java.util.List;
import java.util.stream.Collectors;

import com.betacom.backend.dto.customer.CustomerDTO;
import com.betacom.backend.model.wishlist.Wishlist;

public class WishlistDTO {

	private Long id;
	private CustomerDTO customer;
	private List<WishlistItemDTO> wishlistItems;
	
	public WishlistDTO() {
		super();
	}

	public WishlistDTO(Long id, CustomerDTO customer, List<WishlistItemDTO> wishlistItems) {
		super();
		this.id = id;
		this.customer = customer;
		this.wishlistItems = wishlistItems;
	}

	public WishlistDTO(CustomerDTO customer, List<WishlistItemDTO> wishlistItems) {
		super();
		this.customer = customer;
		this.wishlistItems = wishlistItems;
	}

	public WishlistDTO(Wishlist wishlist) {
		this.id=wishlist.getId();
		this.customer=new CustomerDTO(wishlist.getCustomer());
		this.wishlistItems=wishlist.getWishlistItems().stream()
				.map(w -> new WishlistItemDTO(w))
				.collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

	public List<WishlistItemDTO> getWishlistItems() {
		return wishlistItems;
	}

	public void setWishlistItems(List<WishlistItemDTO> wishlistItems) {
		this.wishlistItems = wishlistItems;
	}

	@Override
	public String toString() {
		return "WishlistDTO [id=" + id + ", customer=" + customer + ", wishlistItems=" + wishlistItems + "]";
	}
}
