package com.betacom.backend.repositories.customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.backend.model.customer.Address;

public interface IAddressRepository extends JpaRepository<Address, Long> {
	Optional<Address> findByCustomerId(Long id);
}
