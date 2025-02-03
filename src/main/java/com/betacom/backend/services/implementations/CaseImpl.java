package com.betacom.backend.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.CaseDTO;
import com.betacom.backend.model.Cases;
import com.betacom.backend.repositories.ICaseRepository;
import com.betacom.backend.request.CaseRequest;
import com.betacom.backend.services.interfaces.CaseServices;

@Service
public class CaseImpl implements CaseServices{

	@Autowired
	ICaseRepository caseRep;

	@Override
	public List<CaseDTO> list() {
		List<Cases> lCase = caseRep.findAll();

        return lCase.stream().map(c ->
                new CaseDTO(c)
        ).collect(Collectors.toList());
	}

	@Override
	public CaseDTO get(Long id) throws Exception {
		 if(id == null){
	            throw new Exception("missing-id");
	        }

	        Optional<Cases> cas = caseRep.findById(id);

	        if(cas.isPresent()){
	            return new CaseDTO(cas.get());
	        }else{
	            throw new Exception("not-found");
	        }
	}

	@Override
	public void create(CaseRequest req) throws Exception {
		if(mancanoAttributi(req))
            throw new Exception("missing-attributes");

		Cases p = new Cases(req);
        caseRep.save(p);
	}

	@Override
	public void update(CaseRequest req) throws Exception {
		if(req.getId() == null){
            throw new Exception("missing-id");
        }

        if( caseRep.findById(req.getId()).isEmpty()){
            throw new Exception("does-not-exists");
        }

        Cases c = new Cases(req);

        caseRep.save(c);
	}

	@Override
	public void delete(Long id) throws Exception {
		if(id == null){
            throw new Exception("missing-id");
        }

		caseRep.deleteById(id);
	}
	
	 private boolean mancanoAttributi(CaseRequest req) {
	        return req.getColor() == null || req.getColor().isBlank()
	                || req.getsize() == null || req.getsize().isBlank();
	    }
}
