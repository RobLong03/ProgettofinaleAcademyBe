package com.betacom.backend.services.interfaces.customer;

import java.util.List;

import com.betacom.backend.dto.customer.AddressDTO;
import com.betacom.backend.request.customer.AddressRequest;

public interface AddressServices {

	List<AddressDTO> listByCustomer(Long customerId) throws Exception;

	AddressDTO get(Long id) throws Exception;

	void create(AddressRequest req) throws Exception;

	void update(AddressRequest req) throws Exception;

	void delete(Long id) throws Exception;
}
