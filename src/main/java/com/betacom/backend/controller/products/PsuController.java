package com.betacom.backend.controller.products;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import com.betacom.backend.dto.products.PsuDTO;
import com.betacom.backend.request.products.PsuRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.services.interfaces.products.PsuServices;

@RestController
@RequestMapping(path="/app/product/psu")
@CrossOrigin(origins = "*")
public class PsuController {

	private PsuServices psuS;
	private Logger log;
	
	// dato che il contruttore va ad essere associato alla classe
	//la stessa viene iniettata
	public PsuController(PsuServices psuS,Logger log) {
		this.psuS = psuS;
		this.log=log;

	}
	//il remove è presente su Product da notare come si evolve la strategia dei childs orphan removal in jpa
	
	@PostMapping("/create")
	public  ResponseBase create (@RequestBody(required = true)PsuRequest req ) {
		
		log.debug("create :" + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			psuS.create(req);
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
		
	
		
	}
	
	@GetMapping("/list")
	public ResponseList<PsuDTO>list(){
		ResponseList<PsuDTO> r = new ResponseList<PsuDTO>();
		r.setRc(true);
		try {

			r.setDati(psuS.list());
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	@PostMapping("/update")
	public ResponseBase update (@RequestBody(required=true) PsuRequest req) {
		log.debug("update :" + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			psuS.update(req);
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

	
	
	
}
