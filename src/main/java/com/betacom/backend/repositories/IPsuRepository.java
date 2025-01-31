package com.betacom.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.backend.model.Psu;

public interface IPsuRepository extends JpaRepository<Psu, Long> {

}
