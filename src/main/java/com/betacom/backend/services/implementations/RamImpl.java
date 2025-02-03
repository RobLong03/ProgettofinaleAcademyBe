package com.betacom.backend.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.RamDTO;
import com.betacom.backend.model.Ram;
import com.betacom.backend.repositories.IRamRepository;
import com.betacom.backend.request.RamRequest;
import com.betacom.backend.services.interfaces.RamServices;

@Service
public class RamImpl implements RamServices {
	
	@Autowired
	IRamRepository ramRep;

	@Override
	public List<RamDTO> list() {
		
		List<Ram> lR=ramRep.findAll();
		
		return lR.stream()
				.map(r -> new RamDTO(r))
				.collect(Collectors.toList());
	}

	@Override
	public RamDTO get(Long id) throws Exception {
		
		if(id==null)
			throw new Exception("missing-id");
		
		Optional<Ram> r=ramRep.findById(id);
		if(r.isEmpty())
			throw new Exception("not-found");
		
		return new RamDTO(r.get());
	}

	@Override
	public void create(RamRequest req) throws Exception {
		
		if(missingAttributes(req))
			throw new Exception("missing-attributes");
		
		Ram r=new Ram(req);
		ramRep.save(r);
	}

	@Override
	public void update(RamRequest req) throws Exception {
	
		if(req.getId()==null)
			throw new Exception("missing-id");
		
		if(ramRep.findById(req.getId()).isEmpty())
			throw new Exception("does-not-exist");
		
		Ram r=new Ram(req);
		ramRep.save(r);
	}
	
	@Override
	public void delete(Long id) throws Exception {
		
		Optional<Ram> r=ramRep.findById(id);
		if(r.isEmpty())
			throw new Exception("not-found");
		
		ramRep.delete(r.get());
	}
	
	private boolean missingAttributes(RamRequest req) {
		
		return req.getBrand()==null || req.getBrand().isBlank()
				|| req.getModel()==null || req.getModel().isBlank()
				|| req.getDescription()==null || req.getDescription().isBlank()
				|| req.getStock()==null
				|| req.getMhz()==null
				|| req.getSize()==null;
	}
}
