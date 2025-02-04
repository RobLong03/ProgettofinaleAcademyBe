package com.betacom.backend.repositories.administrator;

import com.betacom.backend.model.administrator.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdministratorRepository extends JpaRepository<Administrator, Long> {
}
