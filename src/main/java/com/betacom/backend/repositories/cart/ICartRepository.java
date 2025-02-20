package com.betacom.backend.repositories.cart;

import com.betacom.backend.model.wishlist.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.backend.model.cart.Cart;

import java.util.Optional;

public interface ICartRepository extends JpaRepository<Cart, Long>{
    Optional<Cart> findByCustomer_id(Long customerId); //serve per orderImpl.create

    Optional<Cart> findByCustomer_Id(Long id);
}
