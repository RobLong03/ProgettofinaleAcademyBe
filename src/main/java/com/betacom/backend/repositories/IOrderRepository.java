package com.betacom.backend.repositories;

import com.betacom.backend.model.Customer;
import com.betacom.backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByCustomer_Id(Long customerId);
}
