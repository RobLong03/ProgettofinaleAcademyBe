package com.betacom.backend.services.implementations.products;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.backend.services.interfaces.messages.MessageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.products.PsuDTO;
import com.betacom.backend.model.products.Psu;
import com.betacom.backend.repositories.products.IProductRepository;
import com.betacom.backend.repositories.products.IPsuRepository;
import com.betacom.backend.request.products.PsuRequest;
import com.betacom.backend.services.interfaces.products.PsuServices;

@Service
public class PsuImpl implements PsuServices {

	 @Autowired
     IProductRepository prodRep;

	@Autowired
	MessageServices msgS;

	 @Autowired
	 IPsuRepository psuRep;

	@Override
	public List<PsuDTO> list() {
		List<Psu> lpsu=psuRep.findAll();
		return lpsu.stream().map(p->
			new PsuDTO(p)
		).collect(Collectors.toList());
		 
	}

	@Override
	public PsuDTO get(Long id) throws Exception {
		if(id == null){
			throw new Exception(msgS.getMessage("missing-id-get"));
        }

        Optional<Psu> prod = psuRep.findById(id);

        if(prod.isPresent()){
            return new PsuDTO(prod.get());
        }else{
			throw new Exception(msgS.getMessage("does-not-exist-get"));
        }
	}

	@Override
	public void create(PsuRequest req) throws Exception {
		if(mancanoAttributi(req))
			throw new Exception(msgS.getMessage("missing-attributes-create"));

		Psu p = new Psu(req );
         prodRep.save(p);
		
	}

	@Override
	public void update(PsuRequest req) throws Exception {
		 if(req.getId() == null){
			 throw new Exception(msgS.getMessage("missing-id-update"));
	        }

	        if( prodRep.findById(req.getId()).isEmpty()){
				throw new Exception(msgS.getMessage("does-not-exist-update"));

			}

	        Psu p = new Psu(req);

	        prodRep.save(p);
		
	}

	@Override
	public void delete(Long id) throws Exception {
		if(id == null){
			throw new Exception(msgS.getMessage("missing-id-delete"));
	    }

	    prodRep.deleteById(id);
		
	}
	private boolean mancanoAttributi(PsuRequest req) {
        return req.getDescription() == null || req.getDescription().isBlank()
                || req.getBrand() == null || req.getBrand().isBlank()
                || req.getModel() == null || req.getModel().isBlank()
                || req.getStock() == null
                || req.getWatt() == null ;
               

    }
	
	
}
