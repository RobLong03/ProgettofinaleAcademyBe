package com.betacom.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.backend.model.Wishlist;

public interface IWishlistRepository extends JpaRepository<Wishlist, Long> {

	Optional<Wishlist> findByCustomer_Id(Long customerId);
}
