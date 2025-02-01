package com.betacom.backend.services.interfaces;

import java.util.List;

import com.betacom.backend.dto.CustomerDTO;
import com.betacom.backend.request.CustomerRequest;

public interface CustomerSevices {

	List<CustomerDTO> list();

	CustomerDTO get(Long id) throws Exception;

	void create(CustomerRequest req) throws Exception;

	void update(CustomerRequest req) throws Exception;

	void delete(Long id) throws Exception;
}
