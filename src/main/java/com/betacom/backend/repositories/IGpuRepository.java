package com.betacom.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.backend.model.Gpu;


public interface IGpuRepository extends JpaRepository<Gpu, Long> {

}
