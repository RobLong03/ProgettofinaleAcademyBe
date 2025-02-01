package com.betacom.backend.services.interfaces;

import java.util.List;

import com.betacom.backend.dto.CartDTO;
import com.betacom.backend.request.CartRequest;

public interface CartServices {

	List<CartDTO> list();        //list per fare list di tutto

	CartDTO get(Long id) throws Exception;         //get per prendere con id,

    //poi altri get o list con specifiche diverse si decide dopo

    void create(CartRequest req ) throws Exception;

    void update(CartRequest req) throws Exception;

    void delete(Long id) throws Exception ;
}
