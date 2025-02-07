package com.betacom.backend.controller.wishlist;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.backend.dto.wishlist.WishlistItemDTO;
import com.betacom.backend.request.wishlist.WishlistItemRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.response.ResponseObject;
import com.betacom.backend.services.interfaces.wishlist.WishlistItemServices;

@RestController
@RequestMapping("/app/product/wishlistItem")
public class WishlistItemController {

	@Autowired
	Logger log;
	
	@Autowired
	WishlistItemServices wishlIS;
	
	@GetMapping("/list")
	public ResponseList<WishlistItemDTO> list() {
		
		log.debug("list");
		
		ResponseList<WishlistItemDTO> res=new ResponseList<WishlistItemDTO>();
		res.setRc(true);
		res.setDati(wishlIS.list());
		
		return res;
	}
	
	@GetMapping("/get")
	public ResponseObject<WishlistItemDTO> get(@RequestParam Long id) {
		
		log.debug("get");
		
		ResponseObject<WishlistItemDTO>res=new ResponseObject<WishlistItemDTO>();
		res.setRc(true);
		
		try {
			
			res.setDati(wishlIS.get(id));
		} catch (Exception e) {
			
			log.error(e.getMessage());
			
			res.setRc(false);
			res.setMsg(e.getMessage());
		}
		
		return res;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody(required=true) WishlistItemRequest req, @RequestParam Long customerId) {
		
		log.debug("create: "+req);
		
		ResponseBase res=new ResponseBase();
		res.setRc(true);
		
		try {
			
			wishlIS.create(req, customerId);
		} catch (Exception e) {
			
			log.error(e.getMessage());
			
			res.setRc(false);
			res.setMsg(e.getMessage());
		}
		
		return res;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestParam Long id) {
		
		log.debug("delete");
		
		ResponseBase res=new ResponseBase();
		res.setRc(true);
		
		try {
			
			wishlIS.delete(id);
		} catch (Exception e) {
			
			log.error(e.getMessage());
			
			res.setRc(false);
			res.setMsg(e.getMessage());
		}
		
		return res;
	}
}
