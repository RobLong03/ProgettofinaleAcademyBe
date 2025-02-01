package com.betacom.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.backend.model.Cart;

public interface ICartRepository extends JpaRepository<Cart, Long>{

}
