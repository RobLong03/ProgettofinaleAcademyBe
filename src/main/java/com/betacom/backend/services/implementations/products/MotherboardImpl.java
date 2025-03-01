package com.betacom.backend.services.implementations.products;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.products.MotherboardDTO;
import com.betacom.backend.dto.products.ProductDescriptionDTO;
import com.betacom.backend.model.products.Motherboard;
import com.betacom.backend.repositories.products.IMotherboardRepository;
import com.betacom.backend.request.products.MotherboardRequest;
import com.betacom.backend.services.interfaces.messages.MessageServices;
import com.betacom.backend.services.interfaces.products.MotherboardServices;
import com.betacom.backend.services.interfaces.products.ProductDescriptionServices;

@Service
public class MotherboardImpl implements MotherboardServices {
	
	@Autowired
	IMotherboardRepository motherbRep;

	@Autowired
	MessageServices msgS;

	@Autowired
	ProductDescriptionServices pdescS;
	
	@Autowired
    Logger log;
	@Override
	public List<MotherboardDTO> list(String lang) {

		
		List<Motherboard> lM=motherbRep.findAll();
		

		return lM.stream()
                .map(p -> {
                	MotherboardDTO dto = new MotherboardDTO(p);
                   
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
	public MotherboardDTO get(Long id,String lang) throws Exception {
		
		if(id==null)
			throw new Exception(msgS.getMessage("missing-id-get"));


		Optional<Motherboard> prod = motherbRep.findById(id);

        if(prod.isPresent()){
        	MotherboardDTO product = new MotherboardDTO(prod.get());
        	 ProductDescriptionDTO description = pdescS.getDescription(product.getId(), lang);
        	if (description != null)  product.setDescription(description);
            return product;
        }else{
            log.error("missing product");
            throw new Exception(msgS.getMessage("does-not-exist-get"));
        }
    }
	

	@Override
	public String create(MotherboardRequest req) throws Exception {
		
		if(missingAttributes(req))
			throw new Exception(msgS.getMessage("missing-attributes-create"));
		
		Motherboard m=new Motherboard(req);
		motherbRep.save(m);
		
		return ""+motherbRep.findAll().getLast().getId();
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
				|| req.getCpuCompatibility()==null || req.getCpuCompatibility().isBlank()
				|| req.getStock()==null;
	}
}
