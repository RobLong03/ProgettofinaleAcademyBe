package com.betacom.backend.services.implementations.cart;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.backend.services.interfaces.messages.MessageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.cart.CartDTO;
import com.betacom.backend.model.cart.Cart;
import com.betacom.backend.model.cart.CartItem;
import com.betacom.backend.model.customer.Customer;
import com.betacom.backend.model.products.Product;
import com.betacom.backend.repositories.cart.ICartRepository;
import com.betacom.backend.repositories.cart.IcartItemRepository;
import com.betacom.backend.repositories.customer.ICustomerRepository;
import com.betacom.backend.request.cart.CartRequest;
import com.betacom.backend.services.interfaces.cart.CartServices;



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
            return new CartDTO(cas.get());
        }else{
			throw new Exception(msgS.getMessage("does-not-exist-get"));
        }
	}

	@Override
	public void create(CartRequest req) throws Exception {
		if(mancanoAttributi(req))
			throw new Exception(msgS.getMessage("missing-attributes-create"));


		Optional<Customer> custOp = cusR.findById(req.getCustomerId());
		if(custOp.isEmpty())
			throw new Exception(msgS.getMessage("cart-missing-customer"));
		
		List<CartItem> cartIt = req.getItems().stream()
				.map(c -> new CartItem(
						c.getId(),
						new Cart(c.getCartId()),
						new Product(c.getProductId()),
						c.getQuantity(),
						c.getPrice()
						))
				.collect(Collectors.toList());
		
		if(cartIt.isEmpty())
			throw new Exception(msgS.getMessage("cart-no-items"));
		
        Cart p = new Cart();
        p.setCustomer(custOp.get());
        p.setItems(cartIt);
        p.setTotalPrice(req.getTotalPrice());
        
        cartR.save(p);
	}

//	@Override
//	public void update(CartRequest req) throws Exception {
//		if(req.getId() == null){
//			throw new Exception(msgS.getMessage("missing-id-update"));
//        }
//
//        if( cartR.findById(req.getId()).isEmpty()){
//			throw new Exception(msgS.getMessage("does-not-exist-update"));
//        }
//
//        Optional<Customer> custOp = cusR.findById(req.getCustomerId());
//		if(custOp.isEmpty())
//			throw new Exception(msgS.getMessage("cart-missing-customer"));
//		
//		List<CartItem> cartIt = req.getItems().stream()
//				.map(c -> new CartItem(
//						c.getId(),
//						new Cart(c.getCartId()),
//						new Product(c.getProductId()),
//						c.getQuantity(),
//						c.getPrice()
//						))
//				.collect(Collectors.toList());
//		
//		if(cartIt.isEmpty())
//			throw new Exception(msgS.getMessage("cart-no-items"));
//
//        Cart p = new Cart();
//        p.setCustomer(custOp.get());
//        p.setItems(cartIt);
//        p.setTotalPrice(req.getTotalPrice());
//        
//        cartR.save(p);
//	}

	@Override
	public void delete(Long id) throws Exception {
		if(id == null){
			throw new Exception(msgS.getMessage("missing-id-delete"));
        }

		cartR.deleteById(id);
	}

	 private boolean mancanoAttributi(CartRequest req) {
	        return req.getItems() == null
	                || req.getTotalPrice() == null;
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
