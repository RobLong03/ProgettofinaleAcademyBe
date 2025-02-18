package com.betacom.backend.daniel;

import com.betacom.backend.controller.cart.CartController;
import com.betacom.backend.controller.cart.CartItemController;
import com.betacom.backend.controller.customer.AddressController;
import com.betacom.backend.controller.customer.CustomerController;
import com.betacom.backend.controller.order.OrderController;
import com.betacom.backend.controller.products.ProductController;
import com.betacom.backend.dto.cart.CartDTO;
import com.betacom.backend.dto.cart.CartItemDTO;
import com.betacom.backend.dto.customer.CustomerDTO;
import com.betacom.backend.dto.products.ProductDTO;
import com.betacom.backend.model.customer.Customer;
import com.betacom.backend.model.wishlist.Wishlist;
import com.betacom.backend.repositories.customer.ICustomerRepository;
import com.betacom.backend.request.cart.CartItemRequest;
import com.betacom.backend.request.cart.CartRequest;
import com.betacom.backend.request.customer.AddressRequest;
import com.betacom.backend.request.customer.CustomerRequest;
import com.betacom.backend.request.products.ProductRequest;
import com.betacom.backend.services.implementations.cart.CartImpl;
import com.betacom.backend.services.implementations.customer.CustomerImpl;
import com.betacom.backend.services.interfaces.cart.CartItemServices;
import com.betacom.backend.services.interfaces.cart.CartServices;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Transactional
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class orderTestSetup {

    @Autowired
    ProductController prodC;

    @Autowired
    CartServices cartS;

    @Autowired
    Logger log;

    @Autowired
    OrderController orderC;

    @Autowired
    CartController cartC;

    @Autowired
    AddressController addressC;

    @Autowired
    CustomerController customerC;

    @Autowired
    CartItemController cartItemC;

    @Autowired
    private CartItemServices cartItemServices;

    @Test
    @Order(1)
    public void itemSetup() throws Exception {
        ProductRequest preq = new ProductRequest("orderTest","model m1","descrizione d1",50,45D);
        prodC.create(preq);

        preq = new ProductRequest("orderTest","model m2","descrizione d2",50,50D);
        prodC.create(preq);

        preq = new ProductRequest("orderTest", "model m3", "descrizione d3", 30, 60D);
        prodC.create(preq);

        preq = new ProductRequest("orderTest", "model m4", "descrizione d4", 40, 55D);
        prodC.create(preq);

        preq = new ProductRequest("orderTest", "model m5", "descrizione d5", 25, 70D);
        prodC.create(preq);

        preq = new ProductRequest("orderTest", "model m6", "descrizione d6", 60, 40D);
        prodC.create(preq);

        preq = new ProductRequest("orderTest", "model m7", "descrizione d7", 35, 65D);
        prodC.create(preq);

    }

    @Test
    @Order(2)
    public void customerSetup() {

        Long[] customerIds = new Long[4];



        CustomerRequest customerReq = new CustomerRequest("Daniel","bostan","bstvld01d02z129s","daniel@bostan.it","password");
        customerC.create(customerReq);

        customerReq = new CustomerRequest("Roberto","Longo","rblng01d02z012s","roberto@longo.it","password");
        customerC.create(customerReq);

        customerReq = new CustomerRequest("Davide","Lodedo","dvdldo01z02z02","davide@lodedo.it","password");
        customerC.create(customerReq);

        customerReq = new CustomerRequest("Damiano","Serini","dmsrn01d02z012a","damiano@serini.it","password");
        customerC.create(customerReq);

        List<CustomerDTO> customers = customerC.list().getDati();

        customers.stream()
                .filter(c-> c.getEmail().equals("daniel@bostan.it") || c.getEmail().equals("roberto@longo.it") || c.getEmail().equals("davide@lodedo.it") || c.getEmail().equals("damiano@serini.it"))
                .forEach( cc->{
                            switch (cc.getEmail()){
                                case "daniel@bostan.it":
                                    customerIds[0] = cc.getId();
                                    break;
                                case "roberto@longo.it":
                                    customerIds[1] = cc.getId();
                                    break;
                                case "davide@lodedo.it":
                                    customerIds[2] = cc.getId();
                                    break;
                                case "damiano@serini.it":
                                    customerIds[3] = cc.getId();
                                    break;
                            }
                }


                );

        for(Long id : customerIds){
            log.debug("ID:"+id.toString());
        }


        AddressRequest addressRequest = new AddressRequest(customerIds[0],"country","city","postalcode","street",11);
        addressC.create(addressRequest);

        addressRequest = new AddressRequest(customerIds[1],"country","city","postalcode","street",12);
        addressC.create(addressRequest);

        addressRequest = new AddressRequest(customerIds[2],"country","city","postalcode","street",13);
        addressC.create(addressRequest);

        addressRequest = new AddressRequest(customerIds[3],"country","city","postalcode","street",14);
        addressC.create(addressRequest);

        CartRequest cartRequest = new CartRequest();
        cartRequest.setCustomerId(customerIds[0]);
        cartRequest.setTotalPrice(0D);
        cartC.create(cartRequest);

        cartRequest.setCustomerId(customerIds[1]);
        cartC.create(cartRequest);

        cartRequest.setCustomerId(customerIds[2]);
        cartC.create(cartRequest);


        cartRequest.setCustomerId(customerIds[3]);
        cartC.create(cartRequest);

        for (CartDTO c : cartS.list()) {
            log.debug("Carts: "+ c.toString());
        }

    }

    @Test
    @Order(3)
    public void setUpCartItems() throws Exception {

        CartDTO cartDTO = cartS.get(1L);

        CartItemRequest cir = new CartItemRequest();

        List<ProductDTO> prodList = prodC.list().getDati();
        prodList.removeIf(p->!p.getBrand().equals("orderTest"));

        cir.setCartId(cartDTO.getId());
        for(ProductDTO prod : prodList){
            cir.setProductId(prod.getId());
            cir.setQuantity((int) (Math.random() * 6) + 1);
            cartItemC.create(cir);
        }

        cartItemServices.list();


       cartDTO = cartS.get(1L);

        cartDTO.getItems().forEach(i->{log.debug(i.toString());});


    }




}
