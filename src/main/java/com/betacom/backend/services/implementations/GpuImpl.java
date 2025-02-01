package com.betacom.backend.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.GpuDTO;
import com.betacom.backend.model.Gpu;
import com.betacom.backend.repositories.IGpuRepository;
import com.betacom.backend.request.GpuRequest;
import com.betacom.backend.services.interfaces.GpuServices;

@Service
public class GpuImpl implements GpuServices {

	@Autowired
	IGpuRepository gpuRep;

	@Override
	public List<GpuDTO> list() {
		List<Gpu> lgpu = gpuRep.findAll();
		return lgpu.stream().map(p -> new GpuDTO(p)).collect(Collectors.toList());
	}

	@Override
	public GpuDTO get(Long id) throws Exception {
		if (id == null) {
			throw new Exception("missing-id");
		}

		Optional<Gpu> prod = gpuRep.findById(id);

		if (prod.isPresent()) {
			return new GpuDTO(prod.get());
		} else {
			throw new Exception("not-found");
		}
	}

	@Override
	public void create(GpuRequest req) throws Exception {
		if (mancanoAttributi(req))
			throw new Exception("missing-attributes");

		Gpu p = new Gpu(req);
		gpuRep.save(p);

	}

	@Override
	public void update(GpuRequest req) throws Exception {
		 if(req.getId() == null){
	            throw new Exception("missing-id");
	        }

	        if(  gpuRep.findById(req.getId()).isEmpty()){
	            throw new Exception("does-not-exists");
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
