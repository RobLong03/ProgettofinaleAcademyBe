package com.betacom.backend.megatest;

import ch.qos.logback.classic.Logger;
import com.betacom.backend.controller.customer.CustomerController;
import com.betacom.backend.dto.customer.CustomerDTO;
import com.betacom.backend.request.customer.CustomerRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FinalCustomersTest {

    private CustomerController customerC;
    private Logger log;

    @Autowired
    public FinalCustomersTest(CustomerController customerC,Logger log) {
        this.customerC =  customerC;
        this.log = log;
    }


    @Test @Order(1)
    public void customerCreate(){
        CustomerRequest request = new CustomerRequest("Daniel","Bostan","BST01D02E120D","daniel@bostan.it","passwordDaniel");
        customerC.create(request);

        request = new CustomerRequest("Davide","Longo","DVNG01D01E012E","davide@longo.it","passwordDavide");
        customerC.create(request);

        request = new CustomerRequest("Damiano","Serini","DMNS01D01E0123E","damiano@serini.it","passwordDamiano");
        customerC.create(request);

        request = new CustomerRequest("Roberto","Longo","RBNG01D01Z129S","roberto@longo.it","passwordRoberto");
        customerC.create(request);

        request = new CustomerRequest("delete","me","DVN11D01E012E","delete@me.it","passwordDelete");
        customerC.create(request);
    }

     @Test @Order(2)
    public void customerUpdate(){
         CustomerDTO deleteMe = customerC.list().getDati().stream().filter(c->c.getName().equals("delete")&&c.getSurname().equals("me")).findFirst().get();
         Assertions.assertThat(deleteMe.getName()).isEqualTo("delete");
         Assertions.assertThat(customerC.get(deleteMe.getId()).getRc()).isEqualTo(true);

         CustomerRequest request = new CustomerRequest("delete","me","DVN11D01E012E","delete@me.it","passwordOfDelete");
     }



}
