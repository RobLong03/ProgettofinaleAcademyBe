package com.betacom.backend.repositories.wishlist;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.backend.model.wishlist.Wishlist;

public interface IWishlistRepository extends JpaRepository<Wishlist, Long> {

	Optional<Wishlist> findByCustomer_Id(Long customerId);
}
