package com.betacom.backend.services.implementations.products;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.products.ProductDescriptionDTO;
import com.betacom.backend.dto.products.RamDTO;
import com.betacom.backend.model.products.Ram;
import com.betacom.backend.repositories.products.IRamRepository;
import com.betacom.backend.request.products.RamRequest;
import com.betacom.backend.services.interfaces.messages.MessageServices;
import com.betacom.backend.services.interfaces.products.ProductDescriptionServices;
import com.betacom.backend.services.interfaces.products.RamServices;

@Service
public class RamImpl implements RamServices {
	
	@Autowired
	IRamRepository ramRep;

	@Autowired
	MessageServices msgS;

	@Autowired
	ProductDescriptionServices pdescS;

	@Autowired
	Logger log;
	@Override
	public List<RamDTO> list(String lang) {
		
		List<Ram> lR=ramRep.findAll();
		
		return lR.stream()
                .map(p -> {
                	RamDTO dto = new RamDTO(p);
                   
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
	public RamDTO get(Long id,String lang) throws Exception {
		
		if(id==null)
			throw new Exception(msgS.getMessage("missing-id-get"));
		
		Optional<Ram> prod = ramRep.findById(id);

        if(prod.isPresent()){
        	RamDTO product = new RamDTO(prod.get());
        	 ProductDescriptionDTO description = pdescS.getDescription(product.getId(), lang);
        	if (description != null)  product.setDescription(description);
            return product;
        }else{
            log.error("missing product");
            throw new Exception(msgS.getMessage("does-not-exist-get"));
        }
	}

	@Override
	public String create(RamRequest req) throws Exception {
		
		if(missingAttributes(req))
			throw new Exception(msgS.getMessage("missing-attributes-create"));
		
		Ram r=new Ram(req);
		ramRep.save(r);
		
		return ""+ramRep.findAll().getLast().getId();
	}

	@Override
	public void update(RamRequest req) throws Exception {
	
		if(req.getId()==null)
			throw new Exception(msgS.getMessage("missing-id-update"));
		
		if(ramRep.findById(req.getId()).isEmpty())
			throw new Exception(msgS.getMessage("does-not-exist-update"));

		Ram r=new Ram(req);
		ramRep.save(r);
	}
	
	@Override
	public void delete(Long id) throws Exception {
		
		Optional<Ram> r=ramRep.findById(id);
		if(r.isEmpty())
			throw new Exception(msgS.getMessage("does-not-exist-delete"));


		ramRep.delete(r.get());
	}
	
	private boolean missingAttributes(RamRequest req) {
		
		return req.getBrand()==null || req.getBrand().isBlank()
				|| req.getModel()==null || req.getModel().isBlank()
				|| req.getStock()==null
				|| req.getMhz()==null
				|| req.getSize()==null;
	}
}
