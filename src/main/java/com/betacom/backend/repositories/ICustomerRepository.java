package com.betacom.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.backend.model.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {

}
