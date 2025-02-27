package com.betacom.backend.controller.products;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.backend.dto.products.ColorDTO;
import com.betacom.backend.request.products.ColorRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.response.ResponseObject;
import com.betacom.backend.services.interfaces.products.ColorServices;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/app/product/color")
public class ColorController {


	@Autowired
	Logger log;
	
	@Autowired
	ColorServices colS;
	
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody(required = true) ColorRequest req) {
		log.debug("Create case: " + req);
		ResponseBase r = new ResponseBase();
		try {
			colS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/list")
	public ResponseList<ColorDTO> list(){
		log.debug("List case");
		ResponseList<ColorDTO> r = new ResponseList<ColorDTO>();
		try {
			r.setDati(colS.list());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/get")
	public ResponseObject<ColorDTO> get(@RequestParam(required = true) Long id){
		log.debug("Get case with id: " + id);
		ResponseObject<ColorDTO> r = new ResponseObject<ColorDTO>();
		try {
			r.setDati(colS.get(id));
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/update")
	public ResponseBase update(@RequestBody(required = true) ColorRequest req) {
		log.debug("Update case: " + req);
		ResponseBase r = new ResponseBase();
		try {
			colS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	@PostMapping("/delete")
	public ResponseBase delete(@RequestParam(required = true) Long id) {
		log.debug("Delete id: " + id);
		ResponseBase r = new ResponseBase();
		try {
			colS.delete(id);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
}
