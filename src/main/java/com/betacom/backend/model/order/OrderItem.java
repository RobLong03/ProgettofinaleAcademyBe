package com.betacom.backend.model.order;

import com.betacom.backend.model.products.Product;
import com.betacom.backend.model.cart.CartItem;
import jakarta.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    Integer quantity;

    Double unitPrice;

    public OrderItem() {
        super();
    }

    public OrderItem(Product product, Order order, Integer quantity, Double unitPrice) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public OrderItem(Long id, Product product, Order order, Integer quantity, Double unitPrice) {
        this.id = id;
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public OrderItem(CartItem cartItem, Order order ){
        this.product = cartItem.getProduct();
        this.order = order;
        this.quantity = cartItem.getQuantity();
        this.unitPrice = cartItem.getPrice();
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", product=" + product +
                ", order=" + order +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
