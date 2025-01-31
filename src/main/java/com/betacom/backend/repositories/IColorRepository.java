package com.betacom.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.backend.model.Color;

public interface IColorRepository extends JpaRepository<Color, Long>{

}
