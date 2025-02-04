package com.betacom.backend.repositories.products;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.backend.model.products.Color;

public interface IColorRepository extends JpaRepository<Color, Long>{

}
