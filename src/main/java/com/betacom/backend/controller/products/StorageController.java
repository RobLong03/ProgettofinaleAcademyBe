package com.betacom.backend.controller.products;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.betacom.backend.dto.products.StorageDTO;
import com.betacom.backend.request.products.StorageRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.response.ResponseObject;
import com.betacom.backend.services.interfaces.products.StorageServices;

@RestController
@RequestMapping("/app/product/storage")
@CrossOrigin(origins = "*")
public class StorageController {

	@Autowired
	Logger log;
	
	@Autowired
	StorageServices stoS;
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody(required = true) StorageRequest req) {
		log.debug("Create storage: " + req);
		ResponseBase r = new ResponseBase();
		try {
			stoS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/list")
	public ResponseList<StorageDTO> list(){
		log.debug("List storage");
		ResponseList<StorageDTO> r = new ResponseList<StorageDTO>();
		try {
			r.setDati(stoS.list());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/get")
	public ResponseObject<StorageDTO> get(@RequestParam(required = true) Long id){
		log.debug("Get storage with id: " + id);
		ResponseObject<StorageDTO> r = new ResponseObject<StorageDTO>();
		try {
			r.setDati(stoS.get(id));
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/update")
	public ResponseBase update(@RequestBody(required = true) StorageRequest req) {
		log.debug("Update storage: " + req);
		ResponseBase r = new ResponseBase();
		try {
			stoS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
}
