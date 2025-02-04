package com.betacom.backend.repositories.products;

import com.betacom.backend.model.products.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product,Long> {
}
