package com.betacom.backend.controller.products;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.backend.dto.products.CaseDTO;
import com.betacom.backend.request.products.CaseRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.response.ResponseObject;
import com.betacom.backend.services.interfaces.products.CaseServices;

@RestController
@RequestMapping("/app/case")
public class CaseController {

	@Autowired
	Logger log;
	
	@Autowired
	CaseServices caseS;
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody(required = true) CaseRequest req) {
		log.debug("Create case: " + req);
		ResponseBase r = new ResponseBase();
		try {
			caseS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/list")
	public ResponseList<CaseDTO> list(){
		log.debug("List case");
		ResponseList<CaseDTO> r = new ResponseList<CaseDTO>();
		try {
			r.setDati(caseS.list());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/get")
	public ResponseObject<CaseDTO> get(@RequestParam(required = true) Long id){
		log.debug("Get case with id: " + id);
		ResponseObject<CaseDTO> r = new ResponseObject<CaseDTO>();
		try {
			r.setDati(caseS.get(id));
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/update")
	public ResponseBase update(@RequestBody(required = true) CaseRequest req) {
		log.debug("Update case: " + req);
		ResponseBase r = new ResponseBase();
		try {
			caseS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/delete")
	public ResponseBase delete(@RequestParam(required = true) Long id){
		log.debug("Delete case with id: " + id);
		ResponseBase r = new ResponseBase();
		try {
			caseS.delete(id);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
}
