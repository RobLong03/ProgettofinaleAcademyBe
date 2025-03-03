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

import com.betacom.backend.dto.products.PsuDTO;
import com.betacom.backend.request.products.PsuRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.response.ResponseObject;
import com.betacom.backend.services.interfaces.messages.MessageServices;
import com.betacom.backend.services.interfaces.products.ProductDescriptionServices;
import com.betacom.backend.services.interfaces.products.PsuServices;

@RestController
@RequestMapping(path="/app/product/psu")
@CrossOrigin(origins = "*")

public class PsuController {

	@Autowired
	MessageServices msgS;

	@Autowired
	PsuServices servRep;

	@Autowired
	ProductDescriptionServices pdescS;

	@Autowired
	Logger log;
	// il remove è presente su Product da notare come si evolve la strategia dei
	// childs orphan removal in jpa

	@PostMapping("/create")
	public ResponseBase create(@RequestBody(required = true) PsuRequest req) {

		log.debug("create :" + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			r.setMsg(servRep.create(req));
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;

	}

	@GetMapping("/list")
	public ResponseList<PsuDTO> list(@RequestParam(defaultValue = "EN") String lang) {
		ResponseList<PsuDTO> r = new ResponseList<PsuDTO>();
		r.setRc(true);
		try {

			r.setDati(servRep.list(lang));
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

	@GetMapping("/get")
	public ResponseObject<PsuDTO> get(@RequestParam(required = true) Long id,
			@RequestParam(defaultValue = "EN") String lang) {
		log.debug("Get case with id: " + id);
		ResponseObject<PsuDTO> r = new ResponseObject<PsuDTO>();
		try {
			r.setDati(servRep.get(id, lang));
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	@PostMapping("/update")
	public ResponseBase update(@RequestBody(required = true) PsuRequest req) {
		log.debug("update :" + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			servRep.update(req);
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

}
