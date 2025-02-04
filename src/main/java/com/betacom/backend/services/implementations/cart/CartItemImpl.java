package com.betacom.backend.services.implementations.cart;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.cart.CartItemDTO;
import com.betacom.backend.model.cart.CartItem;
import com.betacom.backend.repositories.cart.IcartItemRepository;
import com.betacom.backend.request.cart.CartItemRequest;
import com.betacom.backend.services.interfaces.cart.CartItemServices;

@Service
public class CartItemImpl implements CartItemServices{

	@Autowired
	IcartItemRepository carItR;

	@Override
	public List<CartItemDTO> list() {
		List<CartItem> lCarIt = carItR.findAll();
		
		return lCarIt.stream().map(c -> 
					new CartItemDTO(c)
				).collect(Collectors.toList());
	}

	@Override
	public CartItemDTO get(Long id) throws Exception {
		if(id == null){
            throw new Exception("missing-id");
        }

        Optional<CartItem> cas = carItR.findById(id);

        if(cas.isPresent()){
            return new CartItemDTO(cas.get());
        }else{
            throw new Exception("not-found");
        }
	}

	@Override
	public void create(CartItemRequest req) throws Exception {
		if(mancanoAttributi(req))
            throw new Exception("missing-attributes");

        CartItem p = new CartItem(req);
        carItR.save(p);
	}

	@Override
	public void update(CartItemRequest req) throws Exception {
		if(req.getId() == null){
            throw new Exception("missing-id");
        }

        if( carItR.findById(req.getId()).isEmpty()){
            throw new Exception("does-not-exists");
        }

        CartItem c = new CartItem(req);

        carItR.save(c);
	}

	@Override
	public void delete(Long id) throws Exception {
		if(id == null){
            throw new Exception("missing-id");
        }

		carItR.deleteById(id);
	}
	
	private boolean mancanoAttributi(CartItemRequest req) {
        return req.getCartId() == null ||
        		req.getPrice() == null ||
        		req.getProductId() == null || 
        		req.getQuantity() == null;
    }
}
