package com.betacom.backend.services.interfaces.order;

import com.betacom.backend.dto.order.OrderDTO;
import com.betacom.backend.request.order.OrderRequest;

import java.util.List;

public interface OrderServices {


    List<OrderDTO> list();        //list per fare list di tutti

    OrderDTO get(Long id) throws Exception;         //get per prendere con id,
    //poi altri get o list con specifiche diverse si decide dopo

    List<OrderDTO> listByCustomer(Long customerId) throws Exception;


    /**
    Create a order, customerId and addressId are mandatory parameters
    **/
    void create(OrderRequest req ) throws Exception;

    void update(OrderRequest req) throws Exception;

    void delete(Long id) throws Exception ;

    void updateStatus(OrderRequest req) throws Exception ;

//    void addItemToOrder(OrderItemRequest itemReq) throws Exception;
//
//    void removeItemFromOrder(OrderItemRequest itemReq) throws Exception;
    
}
