package com.betacom.backend.controller.products;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.backend.dto.products.RamDTO;
import com.betacom.backend.request.products.RamRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.response.ResponseObject;
import com.betacom.backend.services.interfaces.products.RamServices;

@RestController
@RequestMapping("app/product/ram")
public class RamController {

	@Autowired
	Logger log;
	
	@Autowired
	RamServices ramS;
	
	@GetMapping("/list")
	public ResponseList<RamDTO> list() {
		
		log.debug("list");
		
		ResponseList<RamDTO> res=new ResponseList<RamDTO>();
		res.setRc(true);
		res.setDati(ramS.list());
		
		return res;
	}
	
	@GetMapping("/get")
	public ResponseObject<RamDTO> get(@RequestParam Long id) {
		
		log.debug("get");
		
		ResponseObject<RamDTO>res= new ResponseObject<RamDTO>();
		res.setRc(true);
		
		try {
			
			res.setDati(ramS.get(id));
		} catch (Exception e) {
			
			log.error(e.getMessage());
			
			res.setRc(false);
			res.setMsg(e.getMessage());
		}
		
		return res;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody(required=true) RamRequest req) {
		
		log.debug("create: "+req);
		
		ResponseBase res=new ResponseBase();
		res.setRc(true);
		
		try {
			
			ramS.create(req);
		} catch (Exception e) {
			
			log.error(e.getMessage());
			
			res.setRc(false);
			res.setMsg(e.getMessage());
		}
		
		return res;
	}
	
	@PostMapping("/update")
	public ResponseBase update(@RequestBody(required=true) RamRequest req) {
		
		log.debug("update: "+req);
		
		ResponseBase res=new ResponseBase();
		res.setRc(true);
		
		try {
			
			ramS.update(req);
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
			
			ramS.delete(id);
		} catch (Exception e) {
			
			log.error(e.getMessage());
			
			res.setRc(false);
			res.setMsg(e.getMessage());
		}
		
		return res;
	}
}
