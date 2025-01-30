package com.betacom.backend.repositories;

import com.betacom.backend.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product,Long> {
}
