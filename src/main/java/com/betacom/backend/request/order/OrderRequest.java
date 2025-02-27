package com.betacom.backend.request.order;

public class OrderRequest {
    private Long id;
    private Long customerId;
    private Long addressId;
    private String status;

    //total price e order date sono gestiti completamente da codice, inutile tenerli come campi

    public OrderRequest() {
    }

    public OrderRequest(Long customerId,  Long addressId) {
        this.customerId = customerId;
        this.addressId = addressId;
    }

    public OrderRequest(Long id, Long customerId, Long addressId) {
        this.id = id;
        this.customerId = customerId;
        this.addressId = addressId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
