package com.betacom.backend.services.interfaces.products;

import java.util.List;

import com.betacom.backend.dto.products.RamDTO;
import com.betacom.backend.request.products.RamRequest;

public interface RamServices {

	List<RamDTO> list(String lang) ;
	
	RamDTO get(Long id,String lang)  throws Exception;
	
	void create(RamRequest req) throws Exception;
	
	void update(RamRequest req) throws Exception;
	
	void delete(Long id) throws Exception;
}
