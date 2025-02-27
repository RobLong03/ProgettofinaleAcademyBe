package com.betacom.backend.controller.cart;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.backend.dto.cart.CartDTO;
import com.betacom.backend.request.cart.CartRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseObject;
import com.betacom.backend.services.interfaces.cart.CartServices;

@RestController
@RequestMapping("/app/cart")
@CrossOrigin(origins = "*")
public class CartController {

	@Autowired
	Logger log;

	@Autowired
	CartServices carS;

	@PostMapping("/create")
	public ResponseBase create(@RequestBody(required = true) CartRequest req) {
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

	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody(required = true) CartRequest req) {
		log.debug("create: " + req);
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

	@PostMapping("/clear")
	public ResponseBase clear(@RequestBody(required = true) CartRequest req) {
		log.debug("clear: " + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			carS.clear(req);
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

	@GetMapping("/get")
	public ResponseObject<CartDTO> get(@RequestParam Long customerId) {

		log.debug("get");

		ResponseObject<CartDTO>res=new ResponseObject<CartDTO>();
		res.setRc(true);

		try {

			res.setDati(carS.get(customerId));
		} catch (Exception e) {

			log.error(e.getMessage());

			res.setRc(false);
			res.setMsg(e.getMessage());
		}

		return res;
	}
}
