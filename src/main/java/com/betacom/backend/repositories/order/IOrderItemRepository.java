package com.betacom.backend.repositories.order;

import com.betacom.backend.dto.order.OrderItemDTO;
import com.betacom.backend.model.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderItemRepository extends JpaRepository<OrderItem,Long> {
    List<OrderItem> findByOrder_id(Long orderId);
}
