package com.betacom.backend.services.implementations.customer;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.customer.CustomerDTO;
import com.betacom.backend.model.customer.Customer;
import com.betacom.backend.model.wishlist.Wishlist;
import com.betacom.backend.repositories.customer.IAddressRepository;
import com.betacom.backend.repositories.customer.ICustomerRepository;
import com.betacom.backend.repositories.wishlist.IWishlistRepository;
import com.betacom.backend.request.customer.CustomerRequest;
import com.betacom.backend.services.interfaces.customer.CustomerSevices;
import com.betacom.backend.services.interfaces.messages.MessageServices;

@Service
public class CustomerImpl implements CustomerSevices {

	@Autowired
	IAddressRepository AddrRep;

    @Autowired
    MessageServices msgS;
	
	@Autowired
	ICustomerRepository CustRep;
	
	@Autowired
	IWishlistRepository wishR;

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
            throw new Exception(msgS.getMessage("missing-id-get"));
        }
        
        Optional<Customer> customerOpt = CustRep.findById(id);
        if (customerOpt.isPresent()) {
            return new CustomerDTO(customerOpt.get());
        } else {
            throw new Exception(msgS.getMessage("does-not-exist-get"));        }
    }

    @Override
    public void create(CustomerRequest req) throws Exception {
        if (mancanoAttributi(req)) {
            throw new Exception(msgS.getMessage("missing-attributes-create"));
        }
       
        if (flagSameEmail(req)) {
        	 throw new Exception(msgS.getMessage("email-already-in-use"));
		}
        
    
        Customer customer = new Customer(req);
   
      //creation Wishlist in customer
        customer = CustRep.saveAndFlush(customer);

        Wishlist wis=new Wishlist();
        wis.setCustomer(customer);
        wishR.save(wis);
    }

    @Override
    public void update(CustomerRequest req) throws Exception {
        if (req.getId() == null) {
            throw new Exception(msgS.getMessage("missing-id-update"));
        }
        
        Optional<Customer> customerOpt = CustRep.findById(req.getId());
        if (customerOpt.isEmpty()) {
            throw new Exception(msgS.getMessage("does-not-exist-update"));
        }
       
       
     
        Customer customer = new Customer(req);
        CustRep.save(customer);
    }

    @Override
    public void delete(Long id) throws Exception {
        if (id == null) {
            throw new Exception(msgS.getMessage("missing-id-delete"));
        }
        CustRep.deleteById(id);
    }
    
    
    private boolean flagSameEmail(CustomerRequest req) {
    	
    	 String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
         Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    	 Matcher matcher = pattern.matcher(req.getEmail());
    	    boolean matchFound = matcher.find();
    	 
    	 boolean flagSameEmail =CustRep.findAll().stream().anyMatch((x)->{
    	    	if ((x.getEmail().equalsIgnoreCase(req.getEmail()))||!(matchFound)) {
    				return true;
    			}
    			return false;
    	    });
    	 
    	 
    	 return flagSameEmail;
    }
    
    private boolean mancanoAttributi(CustomerRequest req) {
        return req.getName() == null || req.getName().isBlank()
                || req.getSurname() == null || req.getSurname().isBlank()
                || req.getTaxId() == null || req.getTaxId().isBlank()
                || req.getEmail() == null || req.getEmail().isBlank()
                || req.getPassword() == null || req.getPassword().isBlank();
    }


}
