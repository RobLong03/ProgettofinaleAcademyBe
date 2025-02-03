package com.betacom.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.backend.model.WishlistItem;

public interface IWishlistItemRepository extends JpaRepository<WishlistItem, Long> {
	
}
