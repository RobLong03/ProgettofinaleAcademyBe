package com.betacom.backend.controller.wishlist;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.web.bind.annotation.*;
>>>>>>> branch 'development' of https://github.com/RobLong03/ProgettofinaleAcademyBe.git

import com.betacom.backend.dto.wishlist.WishlistDTO;
import com.betacom.backend.request.wishlist.WishlistRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.response.ResponseObject;
import com.betacom.backend.services.interfaces.wishlist.WishlistServices;

@RestController
@RequestMapping("app/product/wishlist")
@CrossOrigin(origins = "*")
public class WishlistController {

	@Autowired
	Logger log;
	
	@Autowired
	WishlistServices wishlS;
	
	@GetMapping("/list")
	public ResponseList<WishlistDTO> list() {
		
		log.debug("list");
		
		ResponseList<WishlistDTO> res=new ResponseList<WishlistDTO>();
		res.setRc(true);
		res.setDati(wishlS.list());
		
		return res;
	}
	
	@GetMapping("/get")
	public ResponseObject<WishlistDTO> get(@RequestParam Long id) {
		
		log.debug("get");
		
		ResponseObject<WishlistDTO>res=new ResponseObject<WishlistDTO>();
		res.setRc(true);
		
		try {
			
			res.setDati(wishlS.get(id));
		} catch (Exception e) {
			
			log.error(e.getMessage());
			
			res.setRc(false);
			res.setMsg(e.getMessage());
		}
		
		return res;
	}
	
	@PostMapping("/emptyWishlist")
	public ResponseBase emptyWishlist(@RequestBody WishlistRequest req) {
		
		log.debug("emptyWishlist");
		
		ResponseBase res=new ResponseBase();
		res.setRc(true);
		
		try {
			
			wishlS.emptyWishlist(req.getId());
		} catch (Exception e) {
			
			log.error(e.getMessage());
			
			res.setRc(false);
			res.setMsg(e.getMessage());
		}
		
		return res;
	}
}
