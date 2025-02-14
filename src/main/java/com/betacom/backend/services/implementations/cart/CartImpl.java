package com.betacom.backend.services.implementations.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.cart.CartDTO;
import com.betacom.backend.dto.cart.CartItemDTO;
import com.betacom.backend.model.cart.Cart;
import com.betacom.backend.model.cart.CartItem;
import com.betacom.backend.model.customer.Customer;
import com.betacom.backend.model.products.Product;
import com.betacom.backend.repositories.cart.ICartRepository;
import com.betacom.backend.repositories.cart.IcartItemRepository;
import com.betacom.backend.repositories.customer.ICustomerRepository;
import com.betacom.backend.request.cart.CartItemRequest;
import com.betacom.backend.request.cart.CartRequest;
import com.betacom.backend.services.interfaces.cart.CartServices;
import com.betacom.backend.services.interfaces.messages.MessageServices;



@Service
public class CartImpl implements CartServices{

	@Autowired
	ICartRepository cartR;

	@Autowired
	MessageServices msgS;
	
	@Autowired
	ICustomerRepository cusR;
	
	@Autowired
	IcartItemRepository cartItemR;
	
	@Override
	public List<CartDTO> list() {
		List<Cart> lcart = cartR.findAll();

		/*
		aggiunto questo per essere sicuri che il prezzo totale dei carelli sia giusto quando gli prendiamo
		 */
		lcart.forEach(cart -> {
			if (cart.getItems() != null) { // Evita possibili NullPointerException
				cart.updateTotalPrice();
			}
		});
		
		return lcart.stream().map(c ->
				new CartDTO(c)
				).collect(Collectors.toList());
	}

	@Override
	public CartDTO get(Long id) throws Exception {
		if(id == null){
			throw new Exception(msgS.getMessage("missing-id-get"));
        }

        Optional<Cart> cas = cartR.findById(id);

        if(cas.isPresent()){
			/*
			nel get mi assicurerei che il carello abbia il prezzo totale gisuto, in quanto immagino sara il metodo usato quando il cliente clichhera sul icona,
			quindi lo carica e si dovrebbe vedere il prezzo  -Daniel
			 */
			Cart cart = cas.get();
			cart.updateTotalPrice();
            return new CartDTO(cart);
        }else{
			throw new Exception(msgS.getMessage("does-not-exist-get"));
        }
	}

	@Override
	public void create(CartRequest req) throws Exception {
		Optional<Customer> custOp = cusR.findById(req.getCustomerId());
		if(custOp.isEmpty())
			throw new Exception(msgS.getMessage("cart-missing-customer"));
		
		List<CartItem> lC = new ArrayList<CartItem>();
		
        Cart p = new Cart();
        p.setItems(lC);
        p.setCustomer(custOp.get());

        p.setTotalPrice(req.getTotalPrice());
        
        cartR.save(p);
	}


	@Override
	public void delete(Long id) throws Exception {
		if(id == null){
			throw new Exception(msgS.getMessage("missing-id-delete"));
        }

		cartR.deleteById(id);
	}

	 @Override
		public void clear(CartRequest req) throws Exception {
			if(req.getId() == null){
	            throw new Exception("missing-id");
	        }
			Optional<Cart> ca = cartR.findById(req.getId());
			if(ca.isEmpty())
				throw new Exception("missing-cart");
			
			Cart c = ca.get();
			c.getItems().clear();
			cartR.save(c);
			
		}

		@Override
		public void removeItem(Long cartId, Long itemId) throws Exception {
			Optional<Cart> ca = cartR.findById(cartId);
			if(ca.isEmpty())
				throw new Exception("missing-cart");
			
			Optional<CartItem> ci = cartItemR.findById(itemId);
			if(ci.isEmpty())
				throw new Exception("missing-cart-item");
			
			Cart c = ca.get();
			
			c.getItems().removeIf(i -> i.getId().equals(itemId));
			cartR.save(c);
		}


}
