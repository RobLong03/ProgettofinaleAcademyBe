package com.betacom.backend.services.interfaces;

import java.util.List;

import com.betacom.backend.dto.WishlistDTO;
//import com.betacom.backend.request.WishlistRequest;

public interface WishlistServices {

	List<WishlistDTO> list();
	
	WishlistDTO get(Long id) throws Exception;
	
	void emptyWishlist(Long wishlistId) throws Exception;
}
