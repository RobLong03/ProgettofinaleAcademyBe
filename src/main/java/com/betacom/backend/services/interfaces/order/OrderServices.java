package com.betacom.backend.services.interfaces.order;

import com.betacom.backend.dto.order.OrderDTO;
import com.betacom.backend.request.order.OrderItemRequest;
import com.betacom.backend.request.order.OrderRequest;

import java.util.List;

public interface OrderServices {

    List<OrderDTO> list();        //list per fare list di tutto
    OrderDTO get(Long id) throws Exception;         //get per prendere con id,
    //poi altri get o list con specifiche diverse si decide dopo

    List<OrderDTO> listByCustomer(Long customerId);

    void create(OrderRequest req ) throws Exception;

    void update(OrderRequest req) throws Exception;

    void delete(Long id) throws Exception ;
    
}
