package com.betacom.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.backend.model.Address;

public interface IAddressRepository extends JpaRepository<Address, Long> {

}
