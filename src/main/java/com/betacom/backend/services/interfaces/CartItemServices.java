package com.betacom.backend.services.interfaces;

import java.util.List;

import com.betacom.backend.dto.CartItemDTO;
import com.betacom.backend.request.CartItemRequest;

public interface CartItemServices {

	List<CartItemDTO> list();        //list per fare list di tutto

	CartItemDTO get(Long id) throws Exception;         //get per prendere con id,

    //poi altri get o list con specifiche diverse si decide dopo

    void create(CartItemRequest req ) throws Exception;

    void update(CartItemRequest req) throws Exception;

    void delete(Long id) throws Exception ;
}
