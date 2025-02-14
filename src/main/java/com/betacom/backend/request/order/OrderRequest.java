package com.betacom.backend.request.order;

public class OrderRequest {
    private Long id;
    private Long customerId;
//    private Long cartId;
    //TODO remove cart id, fetch it by customer id....
    private Long addressId;
    private String orderDate;
    private Double totalPrice;

    public OrderRequest() {
    }

    public OrderRequest(Long customerId, /*Long cartId,*/ Long addressId, String orderDate, Double totalPrice) {
        this.customerId = customerId;
//        this.cartId = cartId;
        this.addressId = addressId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public OrderRequest(Long id, Long customerId,  /*Long cartId,*/ Long addressId, String orderDate, Double totalPrice) {
        this.id = id;
        this.customerId = customerId;
//        this.cartId = cartId;
        this.addressId = addressId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

//    public Long getCartId() {
//        return cartId;
//    }

//    public void setCartId(Long cartId) {
//        this.cartId = cartId;
//    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "id=" + id +
                ", customerId=" + customerId +
//                ", cartId=" + cartId +
                ", addressId=" + addressId +
                ", orderDate='" + orderDate + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }


}
