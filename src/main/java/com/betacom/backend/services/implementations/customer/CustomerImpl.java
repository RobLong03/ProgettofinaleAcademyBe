package com.betacom.backend.services.implementations.customer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.customer.CustomerDTO;
import com.betacom.backend.model.customer.Customer;
import com.betacom.backend.repositories.customer.IAddressRepository;
import com.betacom.backend.repositories.customer.ICustomerRepository;
import com.betacom.backend.request.customer.CustomerRequest;
import com.betacom.backend.services.interfaces.customer.CustomerSevices;

@Service
public class CustomerImpl implements CustomerSevices {

	@Autowired
	IAddressRepository AddrRep;
	
	@Autowired
	ICustomerRepository CustRep;

    @Override
    public List<CustomerDTO> list() {
        List<Customer> customers = CustRep.findAll();
        return customers.stream()
                        .map(customer -> new CustomerDTO(customer))
                        .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO get(Long id) throws Exception {
        if (id == null) {
            throw new Exception("missing-id");
        }
        
        Optional<Customer> customerOpt = CustRep.findById(id);
        if (customerOpt.isPresent()) {
            return new CustomerDTO(customerOpt.get());
        } else {
            throw new Exception("not-found");
        }
    }

    @Override
    public void create(CustomerRequest req) throws Exception {
        if (mancanoAttributi(req)) {
            throw new Exception("missing-attributes");
        }
        
    
        Customer customer = new Customer(req);
        CustRep.save(customer);
    }

    @Override
    public void update(CustomerRequest req) throws Exception {
        if (req.getId() == null) {
            throw new Exception("missing-id");
        }
        
        Optional<Customer> customerOpt = CustRep.findById(req.getId());
        if (customerOpt.isEmpty()) {
            throw new Exception("does-not-exists");
        }
        
     
        Customer customer = new Customer(req);
        CustRep.save(customer);
    }

    @Override
    public void delete(Long id) throws Exception {
        if (id == null) {
            throw new Exception("missing-id");
        }
        CustRep.deleteById(id);
    }
    
    
    private boolean mancanoAttributi(CustomerRequest req) {
        return req.getName() == null || req.getName().isBlank()
                || req.getSurname() == null || req.getSurname().isBlank()
                || req.getTaxId() == null || req.getTaxId().isBlank()
                || req.getEmail() == null || req.getEmail().isBlank()
                || req.getPassword() == null || req.getPassword().isBlank();
    }


}
