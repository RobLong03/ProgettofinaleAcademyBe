package com.betacom.backend.services.interfaces.customer;

import java.util.List;

import com.betacom.backend.dto.SignInDTO;
import com.betacom.backend.dto.customer.CustomerDTO;
import com.betacom.backend.request.SignInRequest;
import com.betacom.backend.request.customer.CustomerRequest;

public interface CustomerSevices {

	List<CustomerDTO> list();

	CustomerDTO get(Long id) throws Exception;

	void create(CustomerRequest req) throws Exception;

	void update(CustomerRequest req) throws Exception;

	void delete(Long id) throws Exception;

    SignInDTO signIn(SignInRequest req);
}
