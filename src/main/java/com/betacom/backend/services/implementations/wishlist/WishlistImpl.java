package com.betacom.backend.services.implementations.wishlist;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.backend.services.interfaces.messages.MessageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.backend.dto.wishlist.WishlistDTO;
import com.betacom.backend.model.wishlist.Wishlist;
import com.betacom.backend.repositories.wishlist.IWishlistRepository;
import com.betacom.backend.services.interfaces.wishlist.WishlistServices;

@Service
public class WishlistImpl implements WishlistServices {

	@Autowired
	IWishlistRepository wishlRep;

	@Autowired
	MessageServices msgS;

	@Override
	@Transactional //useful because a lazy fetch strategy has been defined
	public List<WishlistDTO> list() {

		List<Wishlist> lW=wishlRep.findAll();

		return lW.stream()
				.map(w -> new WishlistDTO(w))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional //useful because a lazy fetch strategy has been defined
	public WishlistDTO get(Long id) throws Exception {

		if(id==null)
			throw new Exception(msgS.getMessage("missing-id-get"));

		Optional<Wishlist> w=wishlRep.findById(id);
		if(w.isEmpty())
			throw new Exception(msgS.getMessage("does-not-exist-get"));

		return new WishlistDTO(w.get());
	}

	@Override
	public void emptyWishlist(Long wishlistId) throws Exception {

		if(wishlistId==null)
			throw new Exception(msgS.getMessage("missing-id-wishlist-empty"));

		Optional<Wishlist> w=wishlRep.findById(wishlistId);
		if(w.isEmpty())
			throw new Exception(msgS.getMessage("does-not-exist-wishlist-empty"));

		w.get().getWishlistItems().clear();

		wishlRep.save(w.get());
	}
}
