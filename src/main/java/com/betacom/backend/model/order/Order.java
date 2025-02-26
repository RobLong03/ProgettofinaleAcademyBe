package com.betacom.backend.model.order;

import com.betacom.backend.dto.cart.CartDTO;
import com.betacom.backend.dto.cart.CartItemDTO;
import com.betacom.backend.model.customer.Address;
import com.betacom.backend.model.customer.Customer;
import com.betacom.backend.utils.OrderStatusEnum;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    private Date orderDate;

    @ManyToOne()
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<OrderItem> orderItems;

    private Double totalPrice = 0.0;

        private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = OrderStatusEnum.valueOf(status).toString();
    }

    /*
    quando carico gli items

        if (!orderItems.isEmpty() ) {
            for(OrderItem item : orderItems)
                totalPrice = totalPrice + item.unitPrice * item.quantity;
        }

        quando salvo l'ordine
            this.orderDate = new Date();
     */


    public Order() {
    }

    public Order(Address address, Customer customer, CartDTO cart) {
        //usualy used for create
        this.address = address;
        this.orderDate = new Date();
        this.customer = customer;
        for(CartItemDTO item : cart.getItems()){
            this.totalPrice += item.getPrice() * item.getQuantity();
        }

        orderDate = new Date();
    }

    public Order(Long id, Address address, Date orderDate, Customer customer, List<OrderItem> orderItems, Double totalPrice) {
        //usualy used for update?
        this.id = id;
        this.address = address;
        this.orderDate = orderDate;
        this.customer = customer;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
