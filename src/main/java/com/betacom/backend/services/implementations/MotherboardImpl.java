package com.betacom.backend.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.MotherboardDTO;
import com.betacom.backend.model.Motherboard;
import com.betacom.backend.repositories.IMotherboardRepository;
import com.betacom.backend.request.MotherboardRequest;
import com.betacom.backend.services.interfaces.MotherboardServices;

@Service
public class MotherboardImpl implements MotherboardServices {
	
	@Autowired
	IMotherboardRepository motherbRep;

	@Override
	public List<MotherboardDTO> list() {
		
		List<Motherboard> lM=motherbRep.findAll();
		
		return lM.stream()
				.map(m -> new MotherboardDTO(m))
				.collect(Collectors.toList());
	}

	@Override
	public MotherboardDTO get(Long id) throws Exception {
		
		if(id==null)
			throw new Exception("missing-id");
			
		Optional<Motherboard> m=motherbRep.findById(id);
		if(m.isEmpty())
			throw new Exception("not-found");
		
		return new MotherboardDTO(m.get());
	}

	@Override
	public void create(MotherboardRequest req) throws Exception {
		
		if(missingAttributes(req))
			throw new Exception("missing-attributes");
		
		Motherboard m=new Motherboard(req);
		motherbRep.save(m);
	}

	@Override
	public void update(MotherboardRequest req) throws Exception {
		
		if(req.getId()==null)
			throw new Exception("missing-id");
		
		if(motherbRep.findById(req.getId()).isEmpty())
			throw new Exception("does-not-exist");
		
		Motherboard m=new Motherboard(req);
		motherbRep.save(m);
	}
	
	@Override
	public void delete(Long id) throws Exception {
		
		Optional<Motherboard> m=motherbRep.findById(id);
		if(m.isEmpty())
			throw new Exception("not-found");
		
		motherbRep.delete(m.get());
	}
	
	private boolean missingAttributes(MotherboardRequest req) {
		
		return req.getBrand()==null || req.getBrand().isBlank()
				|| req.getModel()==null || req.getModel().isBlank()
				|| req.getDescription()==null || req.getDescription().isBlank()
				|| req.getCpuCompatibility()==null || req.getCpuCompatibility().isBlank()
				|| req.getStock()==null;
	}
}
