package com.betacom.backend.controller.products;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.backend.dto.products.GpuDTO;
import com.betacom.backend.request.products.GpuRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.services.interfaces.products.GpuServices;

@RestController
@RequestMapping(path="/app/product/gpu")
public class GpuController {

	
	private GpuServices gpuS;
	private Logger log;
	
	// dato che il contruttore va ad essere associato alla classe
	//la stessa viene iniettata
	public GpuController(GpuServices gpuS,Logger log) {
		this.gpuS = gpuS;
		this.log=log;

	}
	
	//il remove è presente su Product da notare come si evolve la strategia dei childs orphan removal in jpa
	
		@PostMapping("/create")
		public  ResponseBase create (@RequestBody(required = true)GpuRequest req ) {
			
			log.debug("create :" + req);
			ResponseBase r = new ResponseBase();
			r.setRc(true);
			try {
				gpuS.create(req);
			} catch (Exception e) {
				r.setMsg(e.getMessage());
				r.setRc(false);
			}
			return r;
			
		
			
		}
		
		@GetMapping("/list")
		public ResponseList<GpuDTO> list(){
			ResponseList<GpuDTO> r = new ResponseList<GpuDTO>();
			r.setRc(true);
			try {

				r.setDati(gpuS.list());
			} catch (Exception e) {
				r.setMsg(e.getMessage());
				r.setRc(false);
			}
			return r;
		}
		@PostMapping("/update")
		public ResponseBase update (@RequestBody(required=true) GpuRequest req) {
			log.debug("update :" + req);
			ResponseBase r = new ResponseBase();
			r.setRc(true);
			try {
				gpuS.update(req);
			} catch (Exception e) {
				r.setMsg(e.getMessage());
				r.setRc(false);
			}
			return r;
		}

		
		
	
}
