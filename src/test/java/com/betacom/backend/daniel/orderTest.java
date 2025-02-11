package com.betacom.backend.daniel;

import com.betacom.backend.controller.customer.CustomerController;
import com.betacom.backend.controller.order.OrderController;
import com.betacom.backend.controller.products.ProductController;
import com.betacom.backend.controller.wishlist.WishlistController;
import com.betacom.backend.model.cart.Cart;
import com.betacom.backend.model.customer.Address;
import com.betacom.backend.model.customer.Customer;
import com.betacom.backend.model.wishlist.Wishlist;
import com.betacom.backend.repositories.cart.ICartRepository;
import com.betacom.backend.repositories.customer.ICustomerRepository;
import com.betacom.backend.repositories.wishlist.IWishlistRepository;
import com.betacom.backend.request.cart.CartRequest;
import com.betacom.backend.request.customer.AddressRequest;
import com.betacom.backend.request.customer.CustomerRequest;
import com.betacom.backend.request.products.ProductRequest;
import com.betacom.backend.request.wishlist.WishlistRequest;
import com.betacom.backend.services.implementations.cart.CartImpl;
import com.betacom.backend.services.implementations.customer.AddressImpl;
import com.betacom.backend.services.implementations.customer.CustomerImpl;
import com.betacom.backend.services.implementations.wishlist.WishlistImpl;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class orderTest {

    @Autowired
    ProductController prodC;

    @Autowired
    Logger log;

    @Autowired
    CustomerController customerC;

    @Autowired
    ICustomerRepository customerRepo;

    @Autowired
    OrderController orderC;

    @Autowired
    AddressImpl addressImpl;

    @Autowired
    CartImpl cartImpl;

    @Test
    @Order(1)
    public void preOrderTest() throws Exception {
        ProductRequest preq = new ProductRequest("brand b1","model m1","descrizione d1",25,4.5);
        prodC.create(preq);

        preq = new ProductRequest("brand b2","model m2","descrizione d2",25,5.5);
        prodC.create(preq);

        preq = new ProductRequest("brand b3","model m3","descrizione d3",15,6.5);
        prodC.create(preq);

        preq = new ProductRequest("brand b4","model m4","descrizione d4",215,16.5);
        prodC.create(preq);

        preq = new ProductRequest("brand b5", "model m5", "descrizione d5", 120, 19.9);
        prodC.create(preq);

        preq = new ProductRequest("brand b6", "model m6", "descrizione d6", 300, 24.5);
        prodC.create(preq);

        preq = new ProductRequest("brand b7", "model m7", "descrizione d7", 150, 12.8);
        prodC.create(preq);

        preq = new ProductRequest("brand b8", "model m8", "descrizione d8", 85, 29.9);
        prodC.create(preq);

        preq = new ProductRequest("brand b9", "model m9", "descrizione d9", 500, 17.3);
        prodC.create(preq);

        preq = new ProductRequest("brand b10", "model m10", "descrizione d10", 220, 21.7);
        prodC.create(preq);

        preq = new ProductRequest("brand b11", "model m11", "descrizione d11", 90, 15.5);
        prodC.create(preq);

        preq = new ProductRequest("brand b12", "model m12", "descrizione d12", 275, 22.0);
        prodC.create(preq);

        preq = new ProductRequest("brand b13", "model m13", "descrizione d13", 320, 18.9);
        prodC.create(preq);

        preq = new ProductRequest("brand b14", "model m14", "descrizione d14", 60, 26.4);
        prodC.create(preq);

        CustomerRequest customerReq = new CustomerRequest("Daniel","bostan","bstvld01d02z129s","daniel@bostan.it","password");
        customerC.create(customerReq);




        customerReq = new CustomerRequest("Roberto","Longo","rblng01d02z012s","roberto@longo.it","password");
        customerC.create(customerReq);

        customerReq = new CustomerRequest("Davide","Lodedo","dvdldo01z02z02","davide@lodedo.it","password");
        customerC.create(customerReq);

        customerReq = new CustomerRequest("Damiano","Serini","dmsrn01d02z012a","damiano@serini.it","password");
        customerC.create(customerReq);

        AddressRequest addressRequest = new AddressRequest(1L,"country","city","postalcode","street",11);
        addressImpl.create(addressRequest);

        addressRequest = new AddressRequest(2L,"country","city","postalcode","street",12);
        addressImpl.create(addressRequest);

        addressRequest = new AddressRequest(3L,"country","city","postalcode","street",13);
        addressImpl.create(addressRequest);

        addressRequest = new AddressRequest(4L,"country","city","postalcode","street",14);
        addressImpl.create(addressRequest);


        //TODO da modificare quando aggiunge creazione di wishlist e cart in custoemrimpl
        CartRequest cartRequest = new CartRequest();
        Wishlist wishlistRequest = new Wishlist();


        cartRequest.setCustomerId(1L);
        cartImpl.create(cartRequest);

        cartRequest.setCustomerId(2L);
        cartImpl.create(cartRequest);

        cartRequest.setCustomerId(3L);
        cartImpl.create(cartRequest);

        cartRequest.setCustomerId(4L);
        cartImpl.create(cartRequest);

        for(Customer c : customerRepo.findAll()){
            log.debug(c.toString());
        }
    }


}
