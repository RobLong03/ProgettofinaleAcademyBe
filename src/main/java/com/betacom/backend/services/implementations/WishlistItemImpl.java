package com.betacom.backend.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.WishlistItemDTO;
import com.betacom.backend.model.Product;
import com.betacom.backend.model.Wishlist;
import com.betacom.backend.model.WishlistItem;
import com.betacom.backend.repositories.IProductRepository;
import com.betacom.backend.repositories.IWishlistItemRepository;
import com.betacom.backend.repositories.IWishlistRepository;
import com.betacom.backend.request.WishlistItemRequest;
import com.betacom.backend.services.interfaces.WishlistItemServices;

@Service
public class WishlistItemImpl implements WishlistItemServices {
	
	@Autowired
	IWishlistRepository wishlRep;
	
	@Autowired
	IWishlistItemRepository wishlistIRep;
	
	@Autowired
	IProductRepository prodRep;

	@Override
	public List<WishlistItemDTO> list() {
		
		List<WishlistItem> lW=wishlistIRep.findAll();
		
		return lW.stream()
				.map(w -> new WishlistItemDTO(w))
				.collect(Collectors.toList());
	}

	@Override
	public WishlistItemDTO get(Long id) throws Exception {
		
		if(id==null)
			throw new Exception("missing-id");
		
		Optional<WishlistItem> w=wishlistIRep.findById(id);
		if(w.isEmpty())
			throw new Exception("not-found");
		
		return new WishlistItemDTO(w.get());
	}

	@Override
	public void create(WishlistItemRequest req, Long customerId) throws Exception {
		
		if(missingAttributes(req))
			throw new Exception("missing-attributes");
		
		if(customerId==null)
			throw new Exception("missing-id");
		
		Optional<Product> p=prodRep.findById(req.getProductId());
		if(p.isEmpty())
			throw new Exception("not-found");
		
		Optional<Wishlist> wCustomer=wishlRep.findByCustomer_Id(customerId);
		if(wCustomer.isEmpty())
			throw new Exception("not-found");
		
		WishlistItem w=new WishlistItem(p.get(), wCustomer.get());
		wishlistIRep.save(w);
	}
	
	@Override
	public void delete(Long id) throws Exception {
		
		if(id==null)
			throw new Exception("missing-id");
		
		Optional<WishlistItem> w=wishlistIRep.findById(id);
		if(w.isEmpty())
			throw new Exception("not-found");

		wishlistIRep.delete(w.get());
	}
	
	private boolean missingAttributes(WishlistItemRequest req) {
		
		return req.getProductId()==null;
	}
}
