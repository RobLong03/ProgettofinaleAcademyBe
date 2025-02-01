package com.betacom.backend.services.interfaces;

import java.util.List;

import com.betacom.backend.dto.AddressDTO;
import com.betacom.backend.request.AddressRequest;

public interface AddressServices {

	List<AddressDTO> list();

	AddressDTO get(Long id) throws Exception;

	void create(AddressRequest req) throws Exception;

	void update(AddressRequest req) throws Exception;

	void delete(Long id) throws Exception;
}
