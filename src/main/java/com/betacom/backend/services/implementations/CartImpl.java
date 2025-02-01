package com.betacom.backend.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.CartDTO;
import com.betacom.backend.model.Cart;
import com.betacom.backend.repositories.ICartRepository;
import com.betacom.backend.request.CartRequest;
import com.betacom.backend.services.interfaces.CartServices;

@Service
public class CartImpl implements CartServices{

	@Autowired
	ICartRepository cartR;
	
	@Override
	public List<CartDTO> list() {
		List<Cart> lcart = cartR.findAll();
		
		return lcart.stream().map(c ->
				new CartDTO(c)
				).collect(Collectors.toList());
	}

	@Override
	public CartDTO get(Long id) throws Exception {
		if(id == null){
            throw new Exception("missing-id");
        }

        Optional<Cart> cas = cartR.findById(id);

        if(cas.isPresent()){
            return new CartDTO(cas.get());
        }else{
            throw new Exception("not-found");
        }
	}

	@Override
	public void create(CartRequest req) throws Exception {
		if(mancanoAttributi(req))
            throw new Exception("missing-attributes");

        Cart p = new Cart(req);
        cartR.save(p);
	}

	@Override
	public void update(CartRequest req) throws Exception {
		if(req.getId() == null){
            throw new Exception("missing-id");
        }

        if( cartR.findById(req.getId()).isEmpty()){
            throw new Exception("does-not-exists");
        }

        Cart c = new Cart(req);

        cartR.save(c);
	}

	@Override
	public void delete(Long id) throws Exception {
		if(id == null){
            throw new Exception("missing-id");
        }

		cartR.deleteById(id);
	}

	 private boolean mancanoAttributi(CartRequest req) {
	        return req.getItems() == null
	                || req.getTotalPrice() == null;
	    }
}
