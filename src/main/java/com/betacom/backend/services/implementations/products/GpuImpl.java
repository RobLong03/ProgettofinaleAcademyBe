package com.betacom.backend.services.implementations.products;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.products.CaseDTO;
import com.betacom.backend.dto.products.CpuDTO;
import com.betacom.backend.dto.products.GpuDTO;
import com.betacom.backend.dto.products.ProductDescriptionDTO;
import com.betacom.backend.model.products.Cases;
import com.betacom.backend.model.products.Gpu;
import com.betacom.backend.repositories.products.IGpuRepository;
import com.betacom.backend.request.products.GpuRequest;
import com.betacom.backend.services.interfaces.messages.MessageServices;
import com.betacom.backend.services.interfaces.products.GpuServices;
import com.betacom.backend.services.interfaces.products.ProductDescriptionServices;

@Service
public class GpuImpl implements GpuServices {

	
	@Autowired
    Logger log;
	@Autowired
	MessageServices msgS;

	@Autowired
	IGpuRepository gpuRep;
	
	@Autowired
	ProductDescriptionServices pdescS;

	@Override
	public List<GpuDTO> list(String lang) {
		 List<Gpu> lGpu = gpuRep.findAll();

		 return lGpu.stream()
	                .map(p -> {
	                	GpuDTO dto = new GpuDTO(p);
	                   
	                    try {
	                        dto.setDescription(pdescS.getDescription(p.getId(), lang));
	                    } catch (Exception e) {
	                    	return dto;
	                    }
	                    return dto;
	                })
	                .collect(Collectors.toList());
	}

	@Override
	public GpuDTO get(Long id,String lang) throws Exception {
		if (id == null) {
			throw new Exception(msgS.getMessage("missing-id-get"));
		}

		Optional<Gpu> prod = gpuRep.findById(id);

        if(prod.isPresent()){
        	GpuDTO product = new GpuDTO(prod.get());
        	 ProductDescriptionDTO description = pdescS.getDescription(product.getId(), lang);
        	if (description != null)  product.setDescription(description);
            return product;
        }else{
            log.error("missing product");
            throw new Exception(msgS.getMessage("does-not-exist-get"));
        }
	}

	@Override
	public void create(GpuRequest req) throws Exception {
		if (mancanoAttributi(req))
			throw new Exception(msgS.getMessage("missing-attributes-create"));

		Gpu p = new Gpu(req);
		gpuRep.save(p);

	}

	@Override
	public void update(GpuRequest req) throws Exception {
		 if(req.getId() == null){
			 throw new Exception(msgS.getMessage("missing-id-update"));
	        }

	        if(  gpuRep.findById(req.getId()).isEmpty()){
				throw new Exception(msgS.getMessage("does-not-exist-update"));
	        }
	        Gpu p = new Gpu(req);

	        gpuRep.save(p);

	}

	private boolean mancanoAttributi(GpuRequest req) {
		return  req.getBrand() == null|| req.getBrand().isBlank() 
				|| req.getModel() == null || req.getModel().isBlank()
				|| req.getStock() == null 
				|| req.getVram() == null 
				|| req.getGhz() == null;
	}

}
