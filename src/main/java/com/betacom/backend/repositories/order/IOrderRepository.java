package com.betacom.backend.repositories.order;

import com.betacom.backend.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByCustomer_Id(Long customerId);
}
