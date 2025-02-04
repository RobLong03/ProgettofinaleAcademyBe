package com.betacom.backend.model.cart;

import java.util.List;

import com.betacom.backend.model.customer.Customer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CartItem> items;

	@OneToOne()
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	private Double totalPrice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Cart(Long id, List<CartItem> items, Double totalPrice,Customer customer) {
		super();
		this.id = id;
		this.items = items;
		this.totalPrice = totalPrice;
		this.customer = customer;
	}

	public Cart(Long id, List<CartItem> items, Customer customer) {
		super();
		//total price da calcolre dopo

		this.id = id;
		this.items = items;
		this.customer = customer;
	}

	public Cart(Long id,Customer customer) {
		super();
		this.id = id;
		this.customer = customer;
	}

	public Cart(Long id) {
		super();
		this.id = id;
	}
	
	public Cart() {
		super();
	}

	public Cart(List<CartItem> items, Double totalPrice) {
		super();
		this.items = items;
		this.totalPrice = totalPrice;
	}

	public Cart(List<CartItem> items) {
		super();
		this.items = items;
		this.totalPrice = 0.0;
		for(CartItem item : items)
			this.totalPrice += item.getPrice() * item.getQuantity();
	}

	public void updateTotalPrice() {
		this.totalPrice = 0.0;
		for(CartItem item : items)
			this.totalPrice += item.getPrice() * item.getQuantity();
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", items=" + items + ", totalPrice=" + totalPrice + "]";
	}
	
}
