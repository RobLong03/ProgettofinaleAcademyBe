package com.betacom.backend.controller.cart;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.betacom.backend.dto.cart.CartItemDTO;
import com.betacom.backend.request.cart.CartItemRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.services.interfaces.cart.CartItemServices;

@RestController
@RequestMapping("/app/cartItem")
@CrossOrigin(origins = "*")
public class CartItemController {

	@Autowired
	Logger log;
	
	@Autowired
	CartItemServices carS;
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody(required = true) CartItemRequest req) {
		log.debug("create: " + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			carS.create(req);
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PostMapping("/remove")
	public ResponseBase remove(@RequestBody(required = true) CartItemRequest req) {
		log.debug("delete: " + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			carS.delete(req.getId());
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

	@PostMapping("/add")
	public ResponseBase add(@RequestBody(required = true) CartItemRequest req) {
		log.debug("add: " + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			carS.add(req);
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

	@PostMapping("/removeItems")
	public ResponseBase removeItems(@RequestBody(required = true) CartItemRequest req) {
		log.debug("add: " + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			carS.remove(req);
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/listByCart")
	public ResponseList<CartItemDTO> listByCart(@RequestParam(required = true) Long id){
		ResponseList<CartItemDTO> r = new ResponseList<CartItemDTO>();
		r.setRc(true);
		try {
			carS.listByCart(id);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
}
