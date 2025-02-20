package com.betacom.backend.services.implementations.customer;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.betacom.backend.dto.SignInDTO;
import com.betacom.backend.dto.customer.CustomeridsDTO;
import com.betacom.backend.model.cart.Cart;
import com.betacom.backend.model.customer.Address;
import com.betacom.backend.model.order.Order;
import com.betacom.backend.repositories.cart.ICartRepository;
import com.betacom.backend.repositories.order.IOrderRepository;
import com.betacom.backend.request.SignInRequest;
import com.betacom.backend.services.PasswordService;
import com.betacom.backend.utils.Roles;
import org.slf4j.Logger;
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

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private ICartRepository cartR;

    @Autowired
    private IOrderRepository  orderR;

    @Autowired
    private IAddressRepository  addressR;

    @Autowired
    Logger log;

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
        customer.setPassword(passwordService.hashPassword(req.getPassword()));
   
      //creation Wishlist in customer
        customer = CustRep.saveAndFlush(customer);

        Wishlist wis=new Wishlist();
        wis.setCustomer(customer);
        wishR.save(wis);

        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setTotalPrice(0d);
        cartR.save(cart);


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
        if(req.getPassword()!=null && !req.getPassword().isBlank()){
            customer.setPassword(passwordService.hashPassword(req.getPassword()));
        }

        CustRep.save(customer);
    }

    @Override
    public void delete(Long id) throws Exception {
        if (id == null) {
            throw new Exception(msgS.getMessage("missing-id-delete"));
        }
        
        //delete wishlist for customer by id when deleting a customer
        Optional<Wishlist> w=wishR.findByCustomer_Id(id);
        w.ifPresent(wishlist -> wishR.delete(wishlist));

        //deleting cart for the customer before deleting the customer
        Optional<Cart> c=cartR.findByCustomer_Id(id);
        c.ifPresent((cart) -> cartR.delete(cart));

        Optional<Address> defAddressopt = addressR.findFirstByCustomer_id(0L);
        Address defAddress = defAddressopt.orElse(null);

        // moving the order of the customer to a default customer when deleting the customer
        Customer defCustomer = CustRep.findById(0L).get();
        List<Order> listOfOrders = orderR.findByCustomer_Id(id);
        log.debug(listOfOrders.toString());
        listOfOrders.forEach(order -> {
                    log.debug("BEFORE"+ order.toString());
                    order.setCustomer(defCustomer);
                    order.setAddress(defAddress);
                    log.debug("AFTER"+order.toString());
                    orderR.save(order);
                }
        );

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

    @Override
    public SignInDTO signIn(SignInRequest req) {

        SignInDTO  resp = new SignInDTO();

        if(req.getUsername() == null || req.getUsername().isBlank() || req.getPwd() == null || req.getPwd().isBlank()){
            resp.setLogged(false);
            return resp;
        }

        Optional<Customer> customer = CustRep.findByEmail(req.getUsername()) ;

        resp.setLogged(false);
        if(customer.isPresent()){
            if(passwordService.checkPassword(req.getPwd(), customer.get().getPassword())){
                resp.setLogged(true);
                resp.setRole(Roles.valueOf("CUSTOMER").toString());
            }
        }


        return resp;
    }

    @Override
    public CustomeridsDTO getCustomerIds(String email) throws Exception {
        if(email == null || email.isBlank()){
            throw new Exception(msgS.getMessage("missing-email"));
        }
        CustomeridsDTO customeridsDTO = new CustomeridsDTO();

        Customer customer = CustRep.findByEmail(email).orElse(null);
        if(customer == null){
            throw new Exception(msgS.getMessage("does-not-exist-customer"));
        }

        customeridsDTO.setCustomerId(customer.getId());

        Wishlist wishlist = wishR.findByCustomer_Id(customer.getId()).orElse(null);
        if(wishlist == null){
            throw new Exception(msgS.getMessage("does-not-exist-wishlist"));
        }
        customeridsDTO.setWishListId(wishlist.getId());

        Cart cart = cartR.findByCustomer_Id(customer.getId()).orElse(null);
        if(cart == null){
            throw new Exception(msgS.getMessage("does-not-exist-cart"));
        }
        customeridsDTO.setCartId(cart.getId());

        return customeridsDTO;
    }


}
