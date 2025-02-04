package com.betacom.backend.repositories.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.backend.model.cart.CartItem;

public interface IcartItemRepository extends JpaRepository<CartItem, Long>{

}
