package com.betacom.backend.model;

import java.util.List;
import java.util.stream.Collectors;

import com.betacom.backend.request.CartRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	private List<CartItem> items;
	
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

	public Cart(Long id, List<CartItem> items, Double totalPrice) {
		super();
		this.id = id;
		this.items = items;
		this.totalPrice = totalPrice;
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
	
	public Cart(CartRequest req) {
		super();
		this.items = req.getItems().stream().map(c -> 
			new CartItem(
                c.getProductId(),
                new Cart(c.getCartId()),
                new Product(c.getProductId()),
                c.getQuantity(),
                c.getPrice()
        		))
        .collect(Collectors.toList()); ;
		this.totalPrice = req.getTotalPrice();
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", items=" + items + ", totalPrice=" + totalPrice + "]";
	}
	
}
