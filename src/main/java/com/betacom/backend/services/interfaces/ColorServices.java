package com.betacom.backend.services.interfaces;

import java.util.List;

import com.betacom.backend.dto.ColorDTO;
import com.betacom.backend.request.ColorRequest;

public interface ColorServices {
	
	List<ColorDTO> list();
	
	ColorDTO get(Long id) throws Exception;
	
	void create(ColorRequest req) throws Exception;
	
	void update(ColorRequest req) throws Exception;
	
	void delete(Long id) throws Exception;
}	
