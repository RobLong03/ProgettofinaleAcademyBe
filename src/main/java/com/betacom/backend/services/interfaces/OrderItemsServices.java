package com.betacom.backend.services.interfaces;

import com.betacom.backend.dto.OrderDTO;
import com.betacom.backend.dto.OrderItemDTO;
import com.betacom.backend.request.OrderItemRequest;
import com.betacom.backend.request.OrderRequest;

import java.util.List;

public interface OrderItemsServices {
    List<OrderItemDTO> list();        //list per fare list di tutto
    List<OrderItemDTO> listByOrder(Long orderId);

    OrderItemDTO get(Long id) throws Exception;         //get per prendere con id,

    //poi altri get o list con specifiche diverse si decide dopo

    void create(OrderItemRequest req ) throws Exception;

    void update(OrderItemRequest req) throws Exception;

    void delete(Long id) throws Exception ;
}
