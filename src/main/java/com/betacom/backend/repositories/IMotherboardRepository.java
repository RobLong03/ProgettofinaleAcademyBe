package com.betacom.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.backend.model.Motherboard;

public interface IMotherboardRepository extends JpaRepository<Motherboard, Long> {

}
