package com.betacom.backend.repositories.order;

import com.betacom.backend.model.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderItemRepository extends JpaRepository<OrderItem,Long> {
}
