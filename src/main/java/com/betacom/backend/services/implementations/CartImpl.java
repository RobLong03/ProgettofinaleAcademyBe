package com.betacom.backend.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.CartDTO;
import com.betacom.backend.model.Cart;
import com.betacom.backend.model.CartItem;
import com.betacom.backend.model.Customer;
import com.betacom.backend.model.Product;
import com.betacom.backend.repositories.ICartRepository;
import com.betacom.backend.repositories.ICustomerRepository;
import com.betacom.backend.request.CartRequest;
import com.betacom.backend.services.interfaces.CartServices;

@Service
public class CartImpl implements CartServices{

	@Autowired
	ICartRepository cartR;
	
	@Autowired
	ICustomerRepository cusR;
	
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

		Optional<Customer> custOp = cusR.findById(req.getCustomerId());
		if(custOp.isEmpty())
			throw new Exception("customer-not-found");
		
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
			throw new Exception("Empty cartitems.");
		
        Cart p = new Cart();
        p.setCustomer(custOp.get());
        p.setItems(cartIt);
        p.setTotalPrice(req.getTotalPrice());
        
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

        Optional<Customer> custOp = cusR.findById(req.getCustomerId());
		if(custOp.isEmpty())
			throw new Exception("customer-not-found");
		
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
			throw new Exception("Empty cartitems.");
		
        Cart p = new Cart();
        p.setCustomer(custOp.get());
        p.setItems(cartIt);
        p.setTotalPrice(req.getTotalPrice());
        
        cartR.save(p);
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
