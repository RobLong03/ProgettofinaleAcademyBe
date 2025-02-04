package com.betacom.backend.services.interfaces.wishlist;

import java.util.List;

import com.betacom.backend.dto.wishlist.WishlistDTO;
//import com.betacom.backend.request.wishlist.WishlistRequest;

public interface WishlistServices {

	List<WishlistDTO> list();
	
	WishlistDTO get(Long id) throws Exception;
	
	void emptyWishlist(Long wishlistId) throws Exception;
}
