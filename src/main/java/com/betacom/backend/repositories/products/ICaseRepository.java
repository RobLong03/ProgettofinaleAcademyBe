package com.betacom.backend.repositories.products;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.backend.model.products.Cases;

public interface ICaseRepository extends JpaRepository<Cases, Long>{

}
