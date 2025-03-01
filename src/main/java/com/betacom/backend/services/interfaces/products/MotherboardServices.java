package com.betacom.backend.services.interfaces.products;

import java.util.List;

import com.betacom.backend.dto.products.MotherboardDTO;
import com.betacom.backend.request.products.MotherboardRequest;

public interface MotherboardServices {

	List<MotherboardDTO> list(String lang) ;
	
	MotherboardDTO get(Long id,String lang)  throws Exception;
	
	String create(MotherboardRequest req) throws Exception;
	
	void update(MotherboardRequest req) throws Exception;
	
	void delete(Long id) throws Exception;
}
