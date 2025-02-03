package com.betacom.backend.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.AddressDTO;
import com.betacom.backend.model.Address;
import com.betacom.backend.repositories.IAddressRepository;
import com.betacom.backend.repositories.ICustomerRepository;
import com.betacom.backend.request.AddressRequest;
import com.betacom.backend.services.interfaces.AddressServices;

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
		if (mancanoAttributi(req))
			throw new Exception("missing-attributes");
		//possibile modifiche per problema con l' id del costumer al momento con 
		//oggetto costumer di classe CostumerRequest		
		//, da testare
		
		//dopo new address il controlllo sul customer e poi assegnazione
		Address p = new Address(req);
		AddrRep.save(p);

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
		return req.getCustomerId() == null || req.getCountry() == null || req.getCountry().isBlank()
				|| req.getCity().isBlank() || req.getCity().isBlank() || req.getPostalCode() == null
				|| req.getPostalCode().isBlank() || req.getStreet() == null || req.getStreet().isBlank()
				|| req.getHouseNumber() == null;
	}

}
