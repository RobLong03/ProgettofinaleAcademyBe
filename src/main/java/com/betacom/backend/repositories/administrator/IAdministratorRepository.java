package com.betacom.backend.repositories.administrator;

import com.betacom.backend.model.administrator.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAdministratorRepository extends JpaRepository<Administrator, Long> {
    Optional<Administrator> findByUsername(String username);
}
