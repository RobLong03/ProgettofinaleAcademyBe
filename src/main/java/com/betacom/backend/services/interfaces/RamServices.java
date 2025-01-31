package com.betacom.backend.services.interfaces;

import java.util.List;

import com.betacom.backend.dto.RamDTO;
import com.betacom.backend.request.RamRequest;

public interface RamServices {

	List<RamDTO> list();
	
	RamDTO get(Long id) throws Exception;
	
	void create(RamRequest req) throws Exception;
	
	void update(RamRequest req) throws Exception;
}
