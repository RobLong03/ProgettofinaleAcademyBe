package com.betacom.backend.repositories.products;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.backend.model.products.Gpu;


public interface IGpuRepository extends JpaRepository<Gpu, Long> {

}
