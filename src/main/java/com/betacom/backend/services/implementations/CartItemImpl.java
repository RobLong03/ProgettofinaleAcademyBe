package com.betacom.backend.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.CartItemDTO;
import com.betacom.backend.model.Cart;
import com.betacom.backend.model.CartItem;
import com.betacom.backend.model.Product;
import com.betacom.backend.repositories.ICartRepository;
import com.betacom.backend.repositories.IProductRepository;
import com.betacom.backend.repositories.IcartItemRepository;
import com.betacom.backend.request.CartItemRequest;
import com.betacom.backend.services.interfaces.CartItemServices;

@Service
public class CartItemImpl implements CartItemServices{

	@Autowired
	IcartItemRepository carItR;
	
	@Autowired
	ICartRepository cartR;
	
	@Autowired
	IProductRepository prodR;

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

		Optional<Cart> cartOptional = cartR.findById(req.getCartId());
	    if(cartOptional.isEmpty())
	    	throw new Exception("cart-not-found");
	        
	    Optional<Product> prodOp = prodR.findById(req.getProductId());
	    if(prodOp.isEmpty())
	    	throw new Exception("prod-not-found");
	     
        CartItem p = new CartItem();
        
        p.setCart(cartOptional.get());
        p.setProduct(prodOp.get());
        p.setPrice(req.getPrice());
        p.setQuantity(req.getQuantity());
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

        Optional<Cart> cartOptional = cartR.findById(req.getCartId());
	    if(cartOptional.isEmpty())
	    	throw new Exception("cart-not-found");
	        
	    Optional<Product> prodOp = prodR.findById(req.getProductId());
	    if(prodOp.isEmpty())
	    	throw new Exception("prod-not-found");
	     
        CartItem p = new CartItem();
        
        p.setCart(cartOptional.get());
        p.setProduct(prodOp.get());
        p.setPrice(req.getPrice());
        p.setQuantity(req.getQuantity());
        carItR.save(p);
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
