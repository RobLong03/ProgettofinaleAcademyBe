package com.betacom.backend.controller.cart;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.betacom.backend.request.cart.CartRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.services.interfaces.cart.CartServices;

@RestController
<<<<<<< HEAD
@RequestMapping("/app/cart")
=======
@RequestMapping("/rest/cart")
@CrossOrigin(origins = "*")
>>>>>>> branch 'development' of https://github.com/RobLong03/ProgettofinaleAcademyBe.git
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
}
