package com.betacom.backend.services.interfaces.cart;

import java.util.List;

import com.betacom.backend.dto.cart.CartItemDTO;
import com.betacom.backend.request.cart.CartItemRequest;

public interface CartItemServices {

	List<CartItemDTO> listByCart(Long cartId);        //list per fare list di tutto

	CartItemDTO get(Long id) throws Exception;         //get per prendere con id,

    //poi altri get o list con specifiche diverse si decide dopo

    void create(CartItemRequest req ) throws Exception;

    void update(CartItemRequest req) throws Exception;

    void add(CartItemRequest req) throws Exception;

    void remove(CartItemRequest req) throws Exception;

    void delete(Long id) throws Exception ;
    
}
