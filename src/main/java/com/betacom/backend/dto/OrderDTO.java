package com.betacom.backend.dto;

import com.betacom.backend.model.Order;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private Long id;
    private CustomerDTO customer;

    private AddressDTO address;
    private Date orderDate;

    private Double totalPrice;

    private List<OrderItemDTO> orderItemsList;

    public OrderDTO() {
    }

    public OrderDTO(Long id, CustomerDTO customer, AddressDTO address, Date orderDate, Double totalPrice, List<OrderItemDTO> orderItemsList) {
        this.id = id;
        this.customer = customer;
        this.address = address;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.orderItemsList = orderItemsList;
    }

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.customer = new CustomerDTO(order.getCustomer());
        this.address = new AddressDTO(order.getAddress());
        this.orderDate = order.getOrderDate();
        this.totalPrice = order.getTotalPrice();
        this.orderItemsList = order.getOrderItems().stream()
                .map(OrderItemDTO::new)
                .toList();
    }
}
