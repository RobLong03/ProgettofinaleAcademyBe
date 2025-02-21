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

import com.betacom.backend.dto.products.GpuDTO;
import com.betacom.backend.request.products.GpuRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.response.ResponseObject;
import com.betacom.backend.services.interfaces.messages.MessageServices;
import com.betacom.backend.services.interfaces.products.GpuServices;
import com.betacom.backend.services.interfaces.products.ProductDescriptionServices;

@RestController
@RequestMapping(path="/app/product/gpu")
@CrossOrigin(origins = "*")
public class GpuController {

	
	@Autowired
    Logger log;
	@Autowired
	MessageServices msgS;

	@Autowired
	GpuServices gpuS;
	
	@Autowired
	ProductDescriptionServices pdescS;
	
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
		public ResponseList<GpuDTO> list(@RequestParam(defaultValue = "EN") String lang){	
			ResponseList<GpuDTO> r = new ResponseList<GpuDTO>();
			r.setRc(true);
			try {

				r.setDati(gpuS.list(lang));
			} catch (Exception e) {
				r.setMsg(e.getMessage());
				r.setRc(false);
			}
			return r;
		}
		@GetMapping("/get")
	    public ResponseObject<GpuDTO> get(@RequestParam Long id ,
	    		@RequestParam(defaultValue = "EN") String lang){
	        log.debug("CC: Gpu get request received for id:"+id+" lang:"+lang);
	        ResponseObject<GpuDTO> r = new  ResponseObject<GpuDTO>();

	        try{
	            r.setDati(gpuS.get(id,lang));
	            r.setRc(true);
	            log.debug("CC: Gpu get done for id:"+id);
	        }catch(Exception e){
	            r.setRc(false);
	            r.setMsg(e.getMessage());
	            log.debug("CC: Error in gpu get for id:"+id);
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
