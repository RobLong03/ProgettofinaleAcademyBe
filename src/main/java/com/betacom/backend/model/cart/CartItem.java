package com.betacom.backend.model.cart;

import com.betacom.backend.model.products.Product;
import com.betacom.backend.request.cart.CartItemRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart_item")
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "cart_id", nullable = false)
	private Cart cart;
	
	@ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
	
	private Integer quantity;
	private Double price;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public CartItem(Long id, Cart cart, Product product, Integer quantity, Double price) {
		super();
		this.id = id;
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}
	public CartItem() {
		super();
	}
	public CartItem(Cart cart, Product product, Integer quantity, Double price) {
		super();
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}
	public CartItem(CartItemRequest req) {
		super();
		this.cart = new Cart(req.getCartId());
		this.product = new Product(req.getProductId());
		this.quantity = req.getQuantity();
		this.price = req.getPrice();
	}
	@Override
	public String toString() {
		return "CartItem [id=" + id + ", cart=" + cart + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}
	public CartItem(Long id) {
		super();
		this.id = id;
	}
}
