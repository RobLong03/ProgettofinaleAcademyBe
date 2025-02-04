package com.betacom.backend.dto;

import com.betacom.backend.model.OrderItem;

public class OrderItemDTO {
    private Long id;
    private ProductDTO product;
    private Long orderId;     //siamo sicuri si gestisca cosi da qua?
    private Integer quantity;
    private Double unitary_price;

    public OrderItemDTO() {
    }

    public OrderItemDTO(Long id, ProductDTO product, Long orderId, Integer quantity, Double unitary_price) {
        this.id = id;
        this.product = product;
        this.orderId = orderId;
        this.quantity = quantity;
        this.unitary_price = unitary_price;
    }

    public OrderItemDTO(OrderItem orderItem){
        this.id = orderItem.getId();
        this.product = new ProductDTO(orderItem.getProduct());
        this.orderId = orderItem.getOrder().getId();
        this.quantity = orderItem.getQuantity();
        this.unitary_price = orderItem.getUnitPrice();
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitary_price() {
        return unitary_price;
    }

    public void setUnitary_price(Double unitary_price) {
        this.unitary_price = unitary_price;
    }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "id=" + id +
                ", product=" + product +
                ", orderId=" + orderId +
                ", quantity=" + quantity +
                ", unitary_price=" + unitary_price +
                '}';
    }
}
