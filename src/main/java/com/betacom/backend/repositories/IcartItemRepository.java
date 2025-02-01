package com.betacom.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.backend.model.CartItem;

public interface IcartItemRepository extends JpaRepository<CartItem, Long>{

}
