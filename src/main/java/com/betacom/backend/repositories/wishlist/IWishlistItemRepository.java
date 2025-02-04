package com.betacom.backend.repositories.wishlist;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.backend.model.wishlist.WishlistItem;

public interface IWishlistItemRepository extends JpaRepository<WishlistItem, Long> {
	
}
