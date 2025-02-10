package com.betacom.backend.repositories.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.backend.model.customer.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {

	
}
