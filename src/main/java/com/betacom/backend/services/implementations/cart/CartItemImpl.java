package com.betacom.backend.services.implementations.cart;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.backend.services.interfaces.messages.MessageServices;

import org.slf4j.Logger;
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
	
	@Autowired
	Logger log;

	@Override
	public List<CartItemDTO> listByCart(Long cartId) {
		List<CartItem> lCarIt = carItR.findByCartId(cartId);

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
	public void create(CartItemRequest req, Long customerId) throws Exception {
		//Controllo attributi --------------------------------------------------------------------------------------------
		if(mancanoAttributi(req))
			throw new Exception(msgS.getMessage("missing-attributes-create"));
		if(customerId==null)
			throw new Exception(msgS.getMessage("missing-customer-id-cartItem-create"));
		Optional<Cart> cartOptional = cartR.findByCustomer_id(customerId);
		if(cartOptional.isEmpty())
			throw new Exception(msgS.getMessage("no-cart-found-cartitem-create"));
		Cart cart = cartOptional.get();
		Optional<Product> prodOp = prodR.findById(req.getProductId());
		if(prodOp.isEmpty())
			throw new Exception(msgS.getMessage("product-not-found-cartitem-create"));
		
		//Se è già stato creato aumenta quantità -------------------------------------------------------------------------
		log.debug("product id:" + req.getProductId().toString());
		Optional<CartItem> existing = cart.getItems().stream().filter(item -> item.getProduct().getId().equals(req.getProductId()))
				.findFirst();
		CartItem p = new CartItem();
		if(existing.isPresent()) {
			p.setQuantity(existing.get().getQuantity() + 1);
			log.debug(p.getQuantity().toString());
			p.setId(existing.get().getId());
		}
		
		p.setCart(cartOptional.get());
		p.setProduct(prodOp.get());
		if(p.getQuantity() == null)
			p.setQuantity(1);
		carItR.save(p);
	}

	/*
	implemento add in modo che possa solo aggiungere quantita a oggetti gia presenti nel carello, per aggiungerli da 0 c'è create
	 */
	@Override
	public void add(CartItemRequest req) throws Exception {
		if(req.getId() == null)
			throw new Exception("Id non presente");
		
		Optional<CartItem> cartItem = carItR.findById(req.getId());
		if(cartItem.isEmpty())
			throw new Exception("Item non presente");
		
		CartItem c = cartItem.get();
		c.setQuantity(c.getQuantity()+1);
		carItR.save(c);
	}

	@Override
	public void remove(CartItemRequest req) throws Exception {
		if(req.getId() == null)
			throw new Exception("Id non presente");
		
		Optional<CartItem> cartItem = carItR.findById(req.getId());
		if(cartItem.isEmpty())
			throw new Exception("Item non presente");
		
		CartItem c = cartItem.get();
		if(c.getQuantity() > 1) {
			c.setQuantity(c.getQuantity()-1);
			carItR.save(c);
		}else {
			carItR.deleteById(req.getId());
		}
	}
	
	

	@Override
	public void update(CartItemRequest req) throws Exception {
		if(req.getId() == null){
			throw new Exception(msgS.getMessage("missing-id-update"));        }

		Optional<CartItem> cartItemOptional = carItR.findById(req.getId());
        if( cartItemOptional.isEmpty()) {
			throw new Exception(msgS.getMessage("does-not-exist-update"));
		}
		CartItem cartItem = cartItemOptional.get();

//        Optional<Cart> cartOptional = cartR.findById(req.getCartId());
//	    if(cartOptional.isEmpty())
//			throw new Exception(msgS.getMessage("no-cart-found-cartitem-update"));

	    Optional<Product> prodOp = prodR.findById(req.getProductId());
	    if(prodOp.isEmpty())
			throw new Exception(msgS.getMessage("product-not-found-cartitem-update"));

		if(cartItem.getProduct().getId() != prodOp.get().getId())
			throw new Exception(msgS.getMessage("product-not-found-cartitem-update"));

		cartItem.setQuantity( req.getQuantity() );
        carItR.save(cartItem);
	}

	@Override
	public void delete(Long id) throws Exception {
		if(id == null){
			throw new Exception(msgS.getMessage("missing-id-delete"));        }

		carItR.deleteById(id);
	}

	private boolean mancanoAttributi(CartItemRequest req) {
		return req.getProductId() == null;
	}

}
