package com.betacom.backend.services.interfaces.order;

import com.betacom.backend.dto.order.OrderItemDTO;
import com.betacom.backend.request.order.OrderItemRequest;

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
