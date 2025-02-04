package com.betacom.backend.repositories.products;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.backend.model.products.Psu;

public interface IPsuRepository extends JpaRepository<Psu, Long> {

}
