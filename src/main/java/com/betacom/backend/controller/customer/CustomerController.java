package com.betacom.backend.controller.customer;

import com.betacom.backend.dto.SignInDTO;
import com.betacom.backend.request.SignInRequest;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import com.betacom.backend.dto.customer.CustomerDTO;
import com.betacom.backend.request.customer.CustomerRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.response.ResponseObject;
import com.betacom.backend.services.interfaces.customer.CustomerSevices;

@RestController
@RequestMapping(path="/app/customer")
@CrossOrigin(origins = "*")
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
	public ResponseBase delete(@RequestBody(required = true) CustomerRequest req) {

		log.debug("delete  :" + req.getId());
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			custS.delete(req.getId());
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
	@GetMapping("/get")
	public ResponseObject<CustomerDTO>get(Long id){
		ResponseObject<CustomerDTO> r = new ResponseObject<CustomerDTO>();
		r.setRc(true);
		try {

			r.setDati(custS.get(id));
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

	@PostMapping("/signIn")
	public SignInDTO signIn(@RequestBody SignInRequest req){
		log.debug("SignIn Request");
		return custS.signIn(req);
	}
	
	
	
}
