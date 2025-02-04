package com.betacom.backend.model.wishlist;

import com.betacom.backend.model.products.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="wishlist_item")
public class WishlistItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn
	private Product product;
	
	@ManyToOne
	@JoinColumn
	private Wishlist wishlist;
	
	public WishlistItem() {
		super();
	}

	public WishlistItem(Long id, Product product, Wishlist wishlist) {
		super();
		this.id = id;
		this.product = product;
		this.wishlist = wishlist;
	}

	public WishlistItem(Long id, Product product) {
		super();
		this.id = id;
		this.product = product;
	}

	public WishlistItem(Product product, Wishlist wishlist) {
		super();
		this.product = product;
		this.wishlist = wishlist;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Wishlist getWishlist() {
		return wishlist;
	}

	public void setWishlist(Wishlist wishlist) {
		this.wishlist = wishlist;
	}

	@Override
	public String toString() {
		return "WishlistItem [id=" + id + ", product=" + product + ", wishlist=" + wishlist + "]";
	}
}
