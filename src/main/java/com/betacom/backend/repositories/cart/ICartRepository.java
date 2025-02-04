package com.betacom.backend.repositories.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.backend.model.cart.Cart;

public interface ICartRepository extends JpaRepository<Cart, Long>{

}
