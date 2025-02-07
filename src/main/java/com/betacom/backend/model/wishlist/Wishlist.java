package com.betacom.backend.model.wishlist;

import java.util.List;

//import com.betacom.backend.request.wishlist.WishlistRequest;

import com.betacom.backend.model.customer.Customer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="wishlist")
public class Wishlist {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn
	private Customer customer;
	
	@OneToMany(
			mappedBy="wishlist", //relationship managed by wishlist attribute in model wishlistItem
			cascade=CascadeType.ALL, //useful for propagating operations on associated entities
			orphanRemoval=true,
			fetch=FetchType.LAZY)
	private List<WishlistItem> wishlistItems;

	public Wishlist() {
		super();
	}

	public Wishlist(Long id, Customer customer) {
		super();
		this.id = id;
		this.customer = customer;
	}

	public Wishlist(Customer customer) {
		super();
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<WishlistItem> getWishlistItems() {
		return wishlistItems;
	}

	public void setWishlistItems(List<WishlistItem> wishlistItems) {
		this.wishlistItems = wishlistItems;
	}

	@Override
	public String toString() {
		return "Wishlist [id=" + id + ", customer=" + customer + ", wishlistItems=" + wishlistItems + "]";
	}
}
