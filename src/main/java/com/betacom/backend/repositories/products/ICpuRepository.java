package com.betacom.backend.repositories.products;

import com.betacom.backend.model.products.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICpuRepository extends JpaRepository<Cpu, Long> {
}
