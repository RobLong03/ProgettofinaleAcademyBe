package com.betacom.backend.dto.customer;

public class CustomeridsDTO {

    private Long customerId,cartId,wishListId;

    public CustomeridsDTO() {
    }

    public CustomeridsDTO(Long customerId, Long cartId, Long wishListId) {
        this.customerId = customerId;
        this.cartId = cartId;
        this.wishListId = wishListId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getWishListId() {
        return wishListId;
    }

    public void setWishListId(Long wishListId) {
        this.wishListId = wishListId;
    }
}
