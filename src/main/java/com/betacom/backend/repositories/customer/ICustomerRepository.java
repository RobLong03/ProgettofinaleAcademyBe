package com.betacom.backend.repositories.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.backend.model.customer.Customer;

import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {


    Optional<Customer> findByEmail(String username);
}
