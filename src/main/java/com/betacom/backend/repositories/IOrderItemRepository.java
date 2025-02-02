package com.betacom.backend.repositories;

import com.betacom.backend.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderItemRepository extends JpaRepository<OrderItem,Long> {
}
