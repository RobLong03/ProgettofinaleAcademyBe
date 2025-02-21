package com.betacom.backend.services.implementations.products;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.products.CaseDTO;
import com.betacom.backend.dto.products.ProductDescriptionDTO;
import com.betacom.backend.model.products.Cases;
import com.betacom.backend.repositories.products.ICaseRepository;
import com.betacom.backend.request.products.CaseRequest;
import com.betacom.backend.services.interfaces.messages.MessageServices;
import com.betacom.backend.services.interfaces.products.CaseServices;
import com.betacom.backend.services.interfaces.products.ProductDescriptionServices;

@Service
public class CaseImpl implements CaseServices{

	 @Autowired
	MessageServices msgS;

	@Autowired
	ICaseRepository caseRep;
	
	@Autowired
	ProductDescriptionServices pdescS;
	
	@Autowired
    Logger log;
	
	@Override
	public List<CaseDTO> list(String lang) {
		List<Cases> lCase = caseRep.findAll();

		return lCase.stream()
                .map(p -> {
                	CaseDTO dto = new CaseDTO(p);
                   
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
	public CaseDTO get(Long id,String lang) throws Exception {
		 if(id == null){
	            throw new Exception(msgS.getMessage("missing-id-get"));
	        }

		 Optional<Cases> prod = caseRep.findById(id);

	        if(prod.isPresent()){
	        	CaseDTO product = new CaseDTO(prod.get());
	        	 ProductDescriptionDTO description = pdescS.getDescription(product.getId(), lang);
	        	if (description != null)  product.setDescription(description);
	            return product;
	        }else{
	            log.error("missing product");
	            throw new Exception(msgS.getMessage("does-not-exist-get"));
	        }
	    }

	@Override
	public void create(CaseRequest req) throws Exception {
		if(mancanoAttributi(req))
            throw new Exception(msgS.getMessage("does-not-exist-get"));

		Cases p = new Cases(req);
        caseRep.save(p);
	}

	@Override
	public void update(CaseRequest req) throws Exception {
		if(req.getId() == null){
			throw new Exception(msgS.getMessage("missing-id-update"));
        }

        if( caseRep.findById(req.getId()).isEmpty()){
			throw new Exception(msgS.getMessage("does-not-exist-update"));
        }

        Cases c = new Cases(req);

        caseRep.save(c);
	}

	@Override
	public void delete(Long id) throws Exception {
		if(id == null){
            throw new Exception(msgS.getMessage("missing-id-delete"));
        }

		caseRep.deleteById(id);
	}
	
	 private boolean mancanoAttributi(CaseRequest req) {
	        return req.getColor() == null || req.getColor().isBlank()
	                || req.getsize() == null || req.getsize().isBlank();
	    }
}
