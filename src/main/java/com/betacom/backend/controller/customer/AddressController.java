package com.betacom.backend.controller.customer;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import com.betacom.backend.dto.customer.AddressDTO;
import com.betacom.backend.request.customer.AddressRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.response.ResponseObject;
import com.betacom.backend.services.interfaces.customer.AddressServices;

@RestController
@RequestMapping(path="/app/address")
@CrossOrigin(origins = "*")
public class AddressController {


	private AddressServices addrS;
	private Logger log;
	
	
	AddressController(AddressServices addrS,Logger log){
		this.addrS=addrS;
		this.log=log;
	}
	
	
	@PostMapping("/create")
	public  ResponseBase create (@RequestBody(required = true)AddressRequest req ) {
		
		log.debug("create :" + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			addrS.create(req);
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
		
	
		
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody(required = true) AddressRequest req) {

		log.debug("delete  :" + req.getId());
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			addrS.delete(req.getId());
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	@GetMapping("/listByCustomer")
	public ResponseList<AddressDTO>listByCustomer(Long customerId) {
		ResponseList<AddressDTO> r = new ResponseList<AddressDTO>();
		r.setRc(true);
		try {

			r.setDati(addrS.listByCustomer(customerId));
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@GetMapping("/get")
	public ResponseObject<AddressDTO>get(Long id){
		ResponseObject<AddressDTO> r = new ResponseObject<AddressDTO>();
		r.setRc(true);
		try {

			r.setDati(addrS.get(id));
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PostMapping("/update")
	public ResponseBase update (@RequestBody(required=true) AddressRequest req) {
		log.debug("update :" + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			addrS.update(req);
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	
	
}
