//package com.betacom.backend.megatest;
//
//import com.betacom.backend.controller.customer.AddressController;
//import com.betacom.backend.controller.customer.CustomerController;
//import com.betacom.backend.dto.SignInDTO;
//import com.betacom.backend.dto.customer.CustomerDTO;
//import com.betacom.backend.request.SignInRequest;
//import com.betacom.backend.request.customer.AddressRequest;
//import com.betacom.backend.request.customer.CustomerRequest;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.slf4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class FinalCustomersTest {
//
//    private CustomerController customerC;
//    private AddressController addressC;
//    private Logger log;
//
//    @Autowired
//    public FinalCustomersTest(CustomerController customerC, org.slf4j.Logger log, AddressController addressC) {
//        this.customerC =  customerC;
//        this.log = log;
//        this.addressC = addressC;
//    }
//
//
//    @Test @Order(1)
//    public void customerCreate(){
//        /*
//        creo 4 customer fissi, ognuno con 2 indirizzi, piu un 5o da modificare e rimuovere
//         */
//
//        CustomerRequest request = new CustomerRequest("Daniel","Bostan","BST01D02E120D","daniel@bostan.it","passwordDaniel");
//        customerC.create(request);
//
//        request = new CustomerRequest("Davide","Longo","DVNG01D01E012E","davide@lodedo.it","passwordDavide");
//        customerC.create(request);
//
//        request = new CustomerRequest("Damiano","Serini","DMNS01D01E0123E","damiano@serini.it","passwordDamiano");
//        customerC.create(request);
//
//        request = new CustomerRequest("Roberto","Longo","RBNG01D01Z129S","roberto@longo.it","passwordRoberto");
//        customerC.create(request);
//
//        request = new CustomerRequest("delete","me","DVN11D01E012E","delete@me.it","passwordDelete");
//        customerC.create(request);
//
//        List<CustomerDTO> dtos = customerC.list().getDati();
//
//        Long danielId, davideId, damianoId, robertoId;
//        danielId = davideId = damianoId = robertoId = 0L;
//
//        for(CustomerDTO dto : dtos){
//            if(dto.getEmail().equals("daniel@bostan.it"))
//                danielId = dto.getId();
//
//            if(dto.getEmail().equals("davide@lodedo.it"))
//                davideId = dto.getId();
//
//            if(dto.getEmail().equals("damiano@serini.it"))
//                damianoId = dto.getId();
//
//            if(dto.getEmail().equals("roberto@longo.it"))
//                robertoId = dto.getId();
//        }
//        AddressRequest addReq = new AddressRequest(danielId,"Italy","Bari","740125","Italia",100);
//        addressC.create(addReq);
//        addReq = new AddressRequest(danielId,"Italy","Bari","740125","Italia",1020);
//        addressC.create(addReq);
//
//        addReq = new AddressRequest(davideId,"Italy","Bari","740125","PingPong",100);
//        addressC.create(addReq);
//        addReq = new AddressRequest(davideId,"Italy","Bari","740125","PingPong",1020);
//        addressC.create(addReq);
//
//        addReq = new AddressRequest(damianoId,"Italy","Bari","740125","Bombetta",100);
//        addressC.create(addReq);
//        addReq = new AddressRequest(damianoId,"Italy","Bari","740125","Bombetta",1020);
//        addressC.create(addReq);
//
//        addReq = new AddressRequest(robertoId,"Italy","Bari","740125","Teatro",100);
//        addressC.create(addReq);
//        addReq = new AddressRequest(davideId,"Italy","Bari","740125","Teatro",1020);
//        addressC.create(addReq);
//
//        Assertions.assertThat(customerC.get(null).getRc()).isEqualTo(false);
//
//    }
//
//     @Test @Order(2)
//    public void customerUpdate(){
//        /*
//        updato deleteMe e poi lo deleto
//         */
//
//         CustomerDTO deleteMe = customerC.list().getDati().stream().filter(c->c.getName().equals("delete")&&c.getSurname().equals("me")).findFirst().get();
//         Assertions.assertThat(deleteMe.getName()).isEqualTo("delete");
//         log.debug(deleteMe.toString());
//
//         SignInDTO resp =  customerC.signIn(new SignInRequest("delete@me.it","passwordDelete"));
//
//         log.debug("Resp:"+resp.getLogged()+" : "+resp.getRole().toString());
//         Assertions.assertThat(customerC.get(deleteMe.getId()).getRc()).isEqualTo(true);
//
//         CustomerRequest request = new CustomerRequest("delete","me","DVN11D01E012E","delete@meme.it","passwordOfDelete");
//         Assertions.assertThat(customerC.update(request).getRc()).isEqualTo(false);
//
//         request = new CustomerRequest(deleteMe.getId(), "delete","me","DVN11D01E012E","delete@meme.it","passwordOfDelete");
//         Assertions.assertThat(customerC.update(request).getRc()).isEqualTo(true);
//
//         deleteMe = customerC.get(deleteMe.getId()).getDati();
//
//         Assertions.assertThat(deleteMe.getName()).isEqualTo("delete");
//         Assertions.assertThat(deleteMe.getSurname()).isEqualTo("me");
//         Assertions.assertThat(deleteMe.getEmail()).isEqualTo("delete@meme.it");
//
//         /*
//        TODO delete customer deleteMe, add cart in customerImpl create, create products
//          */
//
//     }
//
//     @Test
//    @Order(3)
//    public void customerDelete(){
//         CustomerDTO deleteMe = customerC.list().getDati().stream().filter(c->c.getName().equals("delete")&&c.getSurname().equals("me")).findFirst().get();
//         Assertions.assertThat(deleteMe.getName()).isEqualTo("delete");
//         Assertions.assertThat(deleteMe.getSurname()).isEqualTo("me");
//         Assertions.assertThat(deleteMe.getEmail()).isEqualTo("delete@meme.it");
//
//         Assertions.assertThat(customerC.delete(null).getRc()).isEqualTo(false);
//         Assertions.assertThat(customerC.delete(deleteMe.getId()).getRc()).isEqualTo(true);
//         deleteMe = customerC.list().getDati().stream().filter(c->c.getName().equals("delete")&&c.getSurname().equals("me")).findFirst().orElse(null);
//         Assertions.assertThat(deleteMe).isEqualTo(null);
//     }
//
//
//}
