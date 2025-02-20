package com.betacom.backend.controller.products;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.betacom.backend.dto.products.MotherboardDTO;
import com.betacom.backend.request.products.MotherboardRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.response.ResponseObject;
import com.betacom.backend.services.interfaces.products.MotherboardServices;

@RestController
@RequestMapping("app/product/motherboard")
@CrossOrigin(origins = "*")
public class MotherboardController {

	@Autowired
	Logger log;
	
	@Autowired
	MotherboardServices motherbS;
	
	@GetMapping("/list")
	public ResponseList<MotherboardDTO> list() {
		
		log.debug("list");
		
		ResponseList<MotherboardDTO> res=new ResponseList<MotherboardDTO>();
		res.setRc(true);
		res.setDati(motherbS.list());
		
		return res;
	}
	
	@GetMapping("/get")
	public ResponseObject<MotherboardDTO> get(@RequestParam Long id) {
		
		log.debug("get");
		
		ResponseObject<MotherboardDTO> res=new ResponseObject<MotherboardDTO>();
		res.setRc(true);
		
		try {
			
			res.setDati(motherbS.get(id));
		} catch (Exception e) {
			
			log.error(e.getMessage());
			
			res.setRc(false);
			res.setMsg(e.getMessage());
		}
		
		return res;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody(required=true) MotherboardRequest req) {
		
		log.debug("create: "+req);
		
		ResponseBase res=new ResponseBase();
		res.setRc(true);
		
		try {
			
			motherbS.create(req);
		} catch (Exception e) {
			
			log.error(e.getMessage());
			
			res.setRc(false);
			res.setMsg(e.getMessage());
		}
		
		return res;
	}
	
	@PostMapping("/update")
	public ResponseBase update(@RequestBody(required=true) MotherboardRequest req) {
		
		log.debug("update: "+req);
		
		ResponseBase res=new ResponseBase();
		res.setRc(true);
		
		try {
			
			motherbS.update(req);
		} catch (Exception e) {
			
			log.error(e.getMessage());
			
			res.setRc(false);
			res.setMsg(e.getMessage());
		}
		
		return res;
	}
	
}
