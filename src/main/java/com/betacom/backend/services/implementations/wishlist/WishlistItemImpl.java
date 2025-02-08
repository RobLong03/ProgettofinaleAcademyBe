package com.betacom.backend.services.implementations.wishlist;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.backend.services.interfaces.messages.MessageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.wishlist.WishlistItemDTO;
import com.betacom.backend.model.products.Product;
import com.betacom.backend.model.wishlist.Wishlist;
import com.betacom.backend.model.wishlist.WishlistItem;
import com.betacom.backend.repositories.products.IProductRepository;
import com.betacom.backend.repositories.wishlist.IWishlistItemRepository;
import com.betacom.backend.repositories.wishlist.IWishlistRepository;
import com.betacom.backend.request.wishlist.WishlistItemRequest;
import com.betacom.backend.services.interfaces.wishlist.WishlistItemServices;

@Service
public class WishlistItemImpl implements WishlistItemServices {
	@Autowired
	MessageServices msgS;

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
			throw new Exception(msgS.getMessage("missing-id-get"));
		
		Optional<WishlistItem> w=wishlistIRep.findById(id);
		if(w.isEmpty())
			throw new Exception(msgS.getMessage("does-not-exist-get"));
		
		return new WishlistItemDTO(w.get());
	}

	@Override
	public void create(WishlistItemRequest req, Long customerId) throws Exception {
		
		if(missingAttributes(req))
			throw new Exception(msgS.getMessage("missing-attributes-create"));
		
		if(customerId==null)
			throw new Exception(msgS.getMessage("missing-customer-id-wishlistItem-create"));
		
		Optional<Product> p=prodRep.findById(req.getProductId());
		if(p.isEmpty())
			throw new Exception(msgS.getMessage("missing-product-wishlistItem-create"));
		
		Optional<Wishlist> wCustomer=wishlRep.findByCustomer_Id(customerId);
		if(wCustomer.isEmpty())
			throw new Exception(msgS.getMessage("missing-customer-wishlistItem-create"));
		
		WishlistItem w=new WishlistItem(p.get(), wCustomer.get());
		wishlistIRep.save(w);
	}
	
	@Override
	public void delete(Long id) throws Exception {
		
		if(id==null)
			throw new Exception(msgS.getMessage("missing-id-delete"));
		
		Optional<WishlistItem> w=wishlistIRep.findById(id);
		if(w.isEmpty())
			throw new Exception(msgS.getMessage("does-not-exist-delete"));

		wishlistIRep.delete(w.get());
	}
	
	private boolean missingAttributes(WishlistItemRequest req) {
		
		return req.getProductId()==null;
	}
}
