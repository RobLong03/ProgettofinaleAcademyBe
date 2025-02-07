package com.betacom.backend.controller.customer;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.backend.dto.customer.CustomerDTO;
import com.betacom.backend.request.customer.CustomerRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.services.interfaces.customer.CustomerSevices;

@RestController
@RequestMapping(path="/app/customer")
public class CustomerController {

	
	private CustomerSevices custS;
	private Logger log;
	
	
	CustomerController(CustomerSevices custS,Logger log){
		this.custS=custS;
		this.log=log;
	}
	
	
	@PostMapping("/create")
	public  ResponseBase create (@RequestBody(required = true)CustomerRequest req ) {
		
		log.debug("create :" + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			custS.create(req);
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
		
	
		
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(Long id ) {

		log.debug("delete  :" + id);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			custS.delete(id);
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	@GetMapping("/list")
	public ResponseList<CustomerDTO>list(){
		ResponseList<CustomerDTO> r = new ResponseList<CustomerDTO>();
		r.setRc(true);
		try {

			r.setDati(custS.list());
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	@PostMapping("/update")
	public ResponseBase update (@RequestBody(required=true) CustomerRequest req) {
		log.debug("update :" + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			custS.update(req);
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	
	
	
	
}
