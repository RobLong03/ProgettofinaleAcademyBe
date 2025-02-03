package com.betacom.backend.services.interfaces;

import java.util.List;

import com.betacom.backend.dto.MotherboardDTO;
import com.betacom.backend.request.MotherboardRequest;

public interface MotherboardServices {

	List<MotherboardDTO> list();
	
	MotherboardDTO get(Long id) throws Exception;
	
	void create(MotherboardRequest req) throws Exception;
	
	void update(MotherboardRequest req) throws Exception;
	
	void delete(Long id) throws Exception;
}
