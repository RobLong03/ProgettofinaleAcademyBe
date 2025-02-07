package com.betacom.backend.services.implementations.products;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.backend.services.interfaces.messages.MessageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.products.MotherboardDTO;
import com.betacom.backend.model.products.Motherboard;
import com.betacom.backend.repositories.products.IMotherboardRepository;
import com.betacom.backend.request.products.MotherboardRequest;
import com.betacom.backend.services.interfaces.products.MotherboardServices;

@Service
public class MotherboardImpl implements MotherboardServices {
	
	@Autowired
	IMotherboardRepository motherbRep;

	@Autowired
	MessageServices msgS;

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
			throw new Exception(msgS.getMessage("missing-id-get"));


		Optional<Motherboard> m=motherbRep.findById(id);
		if(m.isEmpty())
			throw new Exception(msgS.getMessage("does-not-exist-get"));
		
		return new MotherboardDTO(m.get());
	}

	@Override
	public void create(MotherboardRequest req) throws Exception {
		
		if(missingAttributes(req))
			throw new Exception(msgS.getMessage("missing-attributes-create"));
		
		Motherboard m=new Motherboard(req);
		motherbRep.save(m);
	}

	@Override
	public void update(MotherboardRequest req) throws Exception {
		
		if(req.getId()==null)
			throw new Exception(msgS.getMessage("missing-id-update"));
		
		if(motherbRep.findById(req.getId()).isEmpty())
			throw new Exception(msgS.getMessage("missing-id-update"));

		Motherboard m=new Motherboard(req);
		motherbRep.save(m);
	}
	
	@Override
	public void delete(Long id) throws Exception {
		
		Optional<Motherboard> m=motherbRep.findById(id);
		if(m.isEmpty())
			throw new Exception(msgS.getMessage("does-not-exist-delete"));
		
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
