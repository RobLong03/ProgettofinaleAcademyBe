package com.betacom.backend.services.implementations.products;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.products.GpuDTO;
import com.betacom.backend.model.products.Gpu;
import com.betacom.backend.repositories.products.IGpuRepository;
import com.betacom.backend.request.products.GpuRequest;
import com.betacom.backend.services.interfaces.messages.MessageServices;
import com.betacom.backend.services.interfaces.products.GpuServices;

@Service
public class GpuImpl implements GpuServices {

	@Autowired
	MessageServices msgS;

	@Autowired
	IGpuRepository gpuRep;

	@Override
	public List<GpuDTO> list() {
		 List<Gpu> lGpu = gpuRep.findAll();

	        return lGpu.stream().map(p ->
	                new GpuDTO(p)
	        ).collect(Collectors.toList());
	}

	@Override
	public GpuDTO get(Long id) throws Exception {
		if (id == null) {
			throw new Exception(msgS.getMessage("missing-id-get"));
		}

		Optional<Gpu> prod = gpuRep.findById(id);

		if (prod.isPresent()) {
			return new GpuDTO(prod.get());
		} else {
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
		return req.getDescription() == null || req.getDescription().isBlank() 
				|| req.getBrand() == null|| req.getBrand().isBlank() 
				|| req.getModel() == null || req.getModel().isBlank()
				|| req.getStock() == null 
				|| req.getVram() == null 
				|| req.getGhz() == null;
	}

}
