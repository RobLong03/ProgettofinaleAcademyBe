package com.betacom.backend.repositories.products;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.backend.model.products.Color;


public interface IColorRepository extends JpaRepository<Color, Long>{

	
	
	Optional<Color> findByColor(String color);
}
