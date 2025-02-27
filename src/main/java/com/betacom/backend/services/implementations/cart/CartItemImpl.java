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
		if(mancanoAttributi(req))
			throw new Exception(msgS.getMessage("missing-attributes-create"));

		if(customerId==null)
			throw new Exception(msgS.getMessage("missing-customer-id-cartItem-create"));
		
		Optional<Cart> cartOptional = cartR.findByCustomer_id(customerId);
		if(cartOptional.isEmpty())
			throw new Exception(msgS.getMessage("no-cart-found-cartitem-create"));

		Optional<Product> prodOp = prodR.findById(req.getProductId());
		if(prodOp.isEmpty())
			throw new Exception(msgS.getMessage("product-not-found-cartitem-create"));

		CartItem p = new CartItem();



		p.setCart(cartOptional.get());
		p.setProduct(prodOp.get());
		if(req.getQuantity() == null)
			req.setQuantity(1);
		p.setQuantity(req.getQuantity());
		carItR.save(p);

	}

	/*
	implemento add in modo che possa solo aggiungere quantita a oggetti gia presenti nel carello, per aggiungerli da 0 c'è create
	 */
	@Override
	public void add(CartItemRequest req) throws Exception {

		if(req.getId() == null) {
			throw new Exception(msgS.getMessage("missing-id-update"));
		}

		Optional<CartItem> cartItemOptional = carItR.findById(req.getId());
		if( cartItemOptional.isEmpty()) {
			throw new Exception(msgS.getMessage("does-not-exist-update"));
		}
		CartItem cartItem = cartItemOptional.get();

		/*
		questo check si potrebbe anche non fare? in teoria stiamo modificando un oggetto nel carello, se c'era fino al aggiungerne uno ci sara anche dopo probabilmente?
		nel dubbio better safe than sorry e lasciarlo
		 */
		Optional<Product> prodOp = prodR.findById(req.getProductId());
		if(prodOp.isEmpty())
			throw new Exception(msgS.getMessage("product-not-found-cartitem-update"));
		Product prod = prodOp.get();

		if(cartItem.getProduct().getId() != prod.getId())
			throw new Exception(msgS.getMessage("product-not-found-cartitem-update"));

		cartItem.setQuantity( cartItem.getQuantity() + req.getQuantity() );
		carItR.save(cartItem);



	}

	@Override
	public void remove(CartItemRequest req) throws Exception {
		if (req.getId() == null) {
			throw new Exception(msgS.getMessage("missing-id-update"));
		}

		Optional<CartItem> cartItemOptional = carItR.findById(req.getId());
		if (cartItemOptional.isEmpty()) {
			throw new Exception(msgS.getMessage("does-not-exist-update"));
		}
		CartItem cartItem = cartItemOptional.get();
		System.out.println("********************************************ITEM BEFORE EDITING IT:" + cartItem.getQuantity());

//		Optional<Cart> cartOptional = cartR.findById(req.getCartId());
//		if(cartOptional.isEmpty())
//			throw new Exception(msgS.getMessage("no-cart-found-cartitem-update"));

		Optional<Product> prodOp = prodR.findById(req.getProductId());
		if (prodOp.isEmpty())
			throw new Exception(msgS.getMessage("product-not-found-cartitem-update"));
		Product prod = prodOp.get();

		if (cartItem.getProduct().getId() != prod.getId())
			throw new Exception(msgS.getMessage("product-not-found-cartitem-update"));

		cartItem.setQuantity(cartItem.getQuantity() - req.getQuantity());

		System.out.println("********************************************ITEM AFTER EDITING IT:" + cartItem.getQuantity());

		if (cartItem.getQuantity() > 0) {
			carItR.save(cartItem);
		} else if (cartItem.getQuantity() == 0) {
			carItR.deleteById(cartItem.getId());
		} else {
			throw new Exception(msgS.getMessage("product-quantity-remaining-less-than-zero"));

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
