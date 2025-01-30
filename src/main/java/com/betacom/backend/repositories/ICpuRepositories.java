package com.betacom.backend.repositories;

import com.betacom.backend.model.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICpuRepositories extends JpaRepository<Cpu, Long> {
}
