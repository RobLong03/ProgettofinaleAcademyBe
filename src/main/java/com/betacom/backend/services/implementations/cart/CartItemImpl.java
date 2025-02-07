package com.betacom.backend.services.implementations.cart;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.backend.services.interfaces.messages.MessageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.cart.CartItemDTO;
import com.betacom.backend.model.cart.Cart;
import com.betacom.backend.model.cart.CartItem;
import com.betacom.backend.model.products.Product;
import com.betacom.backend.repositories.cart.ICartRepository;
import com.betacom.backend.repositories.cart.IcartItemRepository;
import com.betacom.backend.repositories.products.IProductRepository;
import com.betacom.backend.request.cart.CartItemRequest;
import com.betacom.backend.services.interfaces.cart.CartItemServices;



@Service
public class CartItemImpl implements CartItemServices{

	@Autowired
	IcartItemRepository carItR;

	@Autowired
	MessageServices msgS;
	
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
			throw new Exception(msgS.getMessage("missing-id-get"));
		}

        Optional<CartItem> cas = carItR.findById(id);

        if(cas.isPresent()){
            return new CartItemDTO(cas.get());
        }else{
			throw new Exception(msgS.getMessage("does-not-exist-get"));
        }
	}

	@Override
	public void create(CartItemRequest req) throws Exception {
		if(mancanoAttributi(req))
			throw new Exception(msgS.getMessage("missing-attributes-create"));

		Optional<Cart> cartOptional = cartR.findById(req.getCartId());
	    if(cartOptional.isEmpty())
			throw new Exception(msgS.getMessage("no-cart-found-cartitem-create"));
	        
	    Optional<Product> prodOp = prodR.findById(req.getProductId());
	    if(prodOp.isEmpty())
			throw new Exception(msgS.getMessage("product-not-found-cartitem-create"));
	     
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
			throw new Exception(msgS.getMessage("missing-id-update"));        }

        if( carItR.findById(req.getId()).isEmpty()) {
			throw new Exception(msgS.getMessage("does-not-exist-update"));
		}

        Optional<Cart> cartOptional = cartR.findById(req.getCartId());
	    if(cartOptional.isEmpty())
			throw new Exception(msgS.getMessage("no-cart-found-cartitem-update"));
	        
	    Optional<Product> prodOp = prodR.findById(req.getProductId());
	    if(prodOp.isEmpty())
			throw new Exception(msgS.getMessage("product-not-found-cartitem-update"));


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
			throw new Exception(msgS.getMessage("missing-id-delete"));        }

		carItR.deleteById(id);
	}
	
	private boolean mancanoAttributi(CartItemRequest req) {
        return req.getCartId() == null ||
        		req.getPrice() == null ||
        		req.getProductId() == null || 
        		req.getQuantity() == null;
    }
}
