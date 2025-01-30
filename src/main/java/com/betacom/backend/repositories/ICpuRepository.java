package com.betacom.backend.repositories;

import com.betacom.backend.model.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICpuRepository extends JpaRepository<Cpu, Long> {
}
