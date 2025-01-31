package com.betacom.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.backend.model.Case;

public interface ICaseRepository extends JpaRepository<Case, Long>{

}
