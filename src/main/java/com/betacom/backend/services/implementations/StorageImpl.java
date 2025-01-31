package com.betacom.backend.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.StorageDTO;
import com.betacom.backend.model.Storage;
import com.betacom.backend.repositories.StorageRepository;
import com.betacom.backend.request.StorageRequest;
import com.betacom.backend.services.interfaces.StorageServices;

@Service
public class StorageImpl implements StorageServices{

	@Autowired
	StorageRepository stoRep;

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
            throw new Exception("missing-id");
        }

        Optional<Storage> storage = stoRep.findById(id);

        if(storage.isPresent()){
            return new StorageDTO(storage.get());
        }else{
            throw new Exception("not-found");
        }
	}

	@Override
	public void create(StorageRequest req) throws Exception {
		 if(mancanoAttributi(req))
	            throw new Exception("missing-attributes");

	        Storage s = new Storage(req);
	         stoRep.save(s);
	}

	@Override
	public void update(StorageRequest req) throws Exception {
		if(req.getId() == null){
            throw new Exception("missing-id");
        }

        if( stoRep.findById(req.getId()).isEmpty()){
            throw new Exception("does-not-exists");
        }

        Storage s = new Storage(req);

        stoRep.save(s);
	}

	@Override
	public void delete(Long id) throws Exception {
		if(id == null){
            throw new Exception("missing-id");
        }

        stoRep.deleteById(id);
	}
	
	private boolean mancanoAttributi(StorageRequest req) {
        return req.getSize() == null
                || req.getType() == null || req.getType().isBlank();
    }
}
