package com.betacom.backend.repositories;

import com.betacom.backend.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProdottoRepositories extends JpaRepository<Prodotto,Integer> {
}
