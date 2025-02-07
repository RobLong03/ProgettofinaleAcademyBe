package com.betacom.backend.services.implementations.customer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.customer.AddressDTO;
import com.betacom.backend.model.customer.Address;
import com.betacom.backend.model.customer.Customer;
import com.betacom.backend.repositories.customer.IAddressRepository;
import com.betacom.backend.repositories.customer.ICustomerRepository;
import com.betacom.backend.request.customer.AddressRequest;
import com.betacom.backend.services.interfaces.customer.AddressServices;

@Service
public class AddressImpl implements AddressServices {

	@Autowired
	IAddressRepository AddrRep;

	@Autowired
	ICustomerRepository CustRep;

	@Override
	public List<AddressDTO> list() {
		List<Address> lAdrs = AddrRep.findAll();

		return lAdrs.stream().map(p -> new AddressDTO(p)).collect(Collectors.toList());
	}

	@Override
	public AddressDTO get(Long id) throws Exception {
		if (id == null) {
			throw new Exception("missing-id");
		}

		Optional<Address> prod = AddrRep.findById(id);

		if (prod.isPresent()) {
			return new AddressDTO(prod.get());
		} else {
			throw new Exception("not-found");
		}
	}

	@Override
	public void create(AddressRequest req) throws Exception {
		if (mancanoAttributi(req)) {
		    throw new Exception("missing-attributes");
		}


		Customer customer = CustRep.findById(req.getCustomerID())
		                           .orElseThrow(() -> new Exception("missing-id"));

		
		Address addr = new Address(req);
		addr.setCustomer(customer);

		AddrRep.save(addr);


	}

	@Override
	public void update(AddressRequest req) throws Exception {
		if (req.getId() == null) {
			throw new Exception("missing-id");
		}

		if (AddrRep.findById(req.getId()).isEmpty()) {
			throw new Exception("does-not-exists");
		}

		Address p = new Address(req);

		AddrRep.save(p);

	}

	@Override
	public void delete(Long id) throws Exception {
		if (id == null) {
			throw new Exception("missing-id");
		}

		AddrRep.deleteById(id);

	}

	private boolean mancanoAttributi(AddressRequest req) {
		return req.getCustomerID() == null || req.getCountry() == null || req.getCountry().isBlank()
				|| req.getCity().isBlank() || req.getPostalCode() == null
				|| req.getPostalCode().isBlank() || req.getStreet() == null || req.getStreet().isBlank()
				|| req.getHouseNumber() == null;
	}

}
