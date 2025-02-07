package com.betacom.backend.services.implementations.products;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.backend.services.interfaces.messages.MessageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.products.StorageDTO;
import com.betacom.backend.model.products.Storage;
import com.betacom.backend.repositories.products.StorageRepository;
import com.betacom.backend.request.products.StorageRequest;
import com.betacom.backend.services.interfaces.products.StorageServices;

@Service
public class StorageImpl implements StorageServices{

	@Autowired
	StorageRepository stoRep;

	@Autowired
	MessageServices msgS;

	@Override
	public List<StorageDTO> list() {
		List<Storage> lStor = stoRep.findAll();

        return lStor.stream().map(s ->
                new StorageDTO(s)
        ).collect(Collectors.toList());
	}

	@Override
	public StorageDTO get(Long id) throws Exception {
		if(id == null){
			throw new Exception(msgS.getMessage("missing-id-get"));
        }

        Optional<Storage> storage = stoRep.findById(id);

        if(storage.isPresent()){
            return new StorageDTO(storage.get());
        }else{
			throw new Exception(msgS.getMessage("does-not-exist-get"));
        }
	}

	@Override
	public void create(StorageRequest req) throws Exception {
		 if(mancanoAttributi(req))
			 throw new Exception(msgS.getMessage("missing-attributes-create"));

	        Storage s = new Storage(req);
	         stoRep.save(s);
	}

	@Override
	public void update(StorageRequest req) throws Exception {
		if(req.getId() == null){
			throw new Exception(msgS.getMessage("missing-id-update"));
        }

        if( stoRep.findById(req.getId()).isEmpty()){
			throw new Exception(msgS.getMessage("does-not-exist-update"));
        }

        Storage s = new Storage(req);

        stoRep.save(s);
	}

	@Override
	public void delete(Long id) throws Exception {
		if(id == null){
			throw new Exception(msgS.getMessage("missing-id-delete"));
        }

        stoRep.deleteById(id);
	}
	
	private boolean mancanoAttributi(StorageRequest req) {
        return req.getSize() == null
                || req.getType() == null || req.getType().isBlank();
    }
}
