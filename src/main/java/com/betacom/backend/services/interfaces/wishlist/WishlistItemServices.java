package com.betacom.backend.services.interfaces.wishlist;

import java.util.List;

import com.betacom.backend.dto.wishlist.WishlistItemDTO;
import com.betacom.backend.request.wishlist.WishlistItemRequest;

public interface WishlistItemServices {

	List<WishlistItemDTO> list();
	
	WishlistItemDTO get(Long id) throws Exception;
	
	void create(WishlistItemRequest req, Long customerId) throws Exception;
		
	void delete(Long id) throws Exception;
}
