package com.betacom.backend.daniel;

import com.betacom.backend.controller.customer.CustomerController;
import com.betacom.backend.controller.order.OrderController;
import com.betacom.backend.controller.order.OrderItemController;
import com.betacom.backend.controller.products.ProductController;
import com.betacom.backend.dto.cart.CartDTO;
import com.betacom.backend.dto.customer.CustomerDTO;
import com.betacom.backend.dto.order.OrderDTO;
import com.betacom.backend.dto.order.OrderItemDTO;
import com.betacom.backend.dto.products.ProductDTO;
import com.betacom.backend.request.order.OrderItemRequest;
import com.betacom.backend.request.order.OrderRequest;
import com.betacom.backend.request.products.ProductRequest;
import com.betacom.backend.services.interfaces.cart.CartServices;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@SpringBootTest
@Transactional
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testOrder {

    @Autowired
    Logger log;

    @Autowired
    OrderController orderC;

    @Autowired
    CustomerController customerC;

    @Autowired
    ProductController productC;

    @Autowired
    OrderItemController orderItemC;

    @Autowired
    CartServices cartS;

    @Test
    @Order(1)
    public void createOrder(){
        CustomerDTO daniel = customerC.list().getDati().stream().filter(c->c.getEmail().equals("daniel@bostan.it")).findFirst().get();
        log.debug(daniel.toString());

        CartDTO carelloDiDaniel = cartS.list().stream().filter(c-> Objects.equals(c.getCustomer().getId(), daniel.getId())).findFirst().get();
        log.debug(carelloDiDaniel.toString());

        OrderRequest orderRequest = new OrderRequest(daniel.getId(),daniel.getAddresses().getFirst().getId(), null ,0d );

        Assertions.assertThat(orderC.create(orderRequest).getRc()).isEqualTo(true);

    }

    @Test
    @Order(2)
    public void ControlliVari(){
        CustomerDTO daniel = customerC.list().getDati().stream().filter(c->c.getEmail().equals("daniel@bostan.it")).findFirst().get();
        orderC.list().getDati().forEach(o->System.out.println(o.toString()));

        OrderDTO orderDiDaniel = orderC.listByCustomer(daniel.getId()).getDati().getFirst();

        log.debug(orderDiDaniel.toString());

        Double prezzoTotale = 0D;
        for(OrderItemDTO oid : orderDiDaniel.getOrderItemsList()){
            log.debug(oid.toString());
            prezzoTotale+=(oid.getUnitary_price()*oid.getQuantity());
        }

        Assertions.assertThat(prezzoTotale).isEqualTo(orderDiDaniel.getTotalPrice());

        log.debug(orderC.get(orderDiDaniel.getId()).getDati().toString());
    }

    @Test
    @Order(1)
    public void getOrder(){
        Assertions.assertThat(orderC.get(null).getRc()).isEqualTo(false);
        Assertions.assertThat(orderC.get(99999999999L).getRc()).isEqualTo(false);

    }

    @Test
    @Order(2)
    public void listByCustomer(){
        Assertions.assertThat(orderC.listByCustomer(null).getRc()).isEqualTo(false);
        Assertions.assertThat(orderC.listByCustomer(99999999999L).getRc()).isEqualTo(true);

    }

    @Test
    @Order(3)
    public void createAllGreen(){
        OrderRequest req = new OrderRequest();

        Assertions.assertThat(orderC.create(req).getRc()).isEqualTo(false);

        req.setAddressId(1L);
        Assertions.assertThat(orderC.create(req).getRc()).isEqualTo(false);

       req.setCustomerId(9999999L);
       Assertions.assertThat(orderC.create(req).getRc()).isEqualTo(false);

       req.setCustomerId(1L);
       req.setAddressId(99999999999L);
       Assertions.assertThat(orderC.create(req).getRc()).isEqualTo(false);
    }

    @Test
    @Order(4)
    public void updateGreen(){
        OrderRequest req = new OrderRequest();
        Assertions.assertThat(orderC.update(req).getRc()).isEqualTo(false);

        req.setId(9999999L);
        Assertions.assertThat(orderC.update(req).getRc()).isEqualTo(false);

        req.setAddressId(9999999L);
        Assertions.assertThat(orderC.update(req).getRc()).isEqualTo(false);

        req.setId(1L);
        Assertions.assertThat(orderC.update(req).getRc()).isEqualTo(false);

        req.setAddressId(3L);
        Assertions.assertThat(orderC.update(req).getRc()).isEqualTo(false);

        req.setAddressId(1L);
        Assertions.assertThat(orderC.update(req).getRc()).isEqualTo(true);

    }

    @Test
    @Order(5)
    public void orderItemVari(){

        Assertions.assertThat(orderItemC.listByOrder(null).getRc()).isEqualTo(false);
        Assertions.assertThat(orderItemC.listByOrder(99999999999999L).getRc()).isEqualTo(false);

        orderItemC.listByOrder(1L).getDati().forEach(a->log.debug(a.toString()));

        Assertions.assertThat(orderItemC.get(null).getRc()).isEqualTo(false);
        Assertions.assertThat(orderItemC.get(99999999999999L).getRc()).isEqualTo(false);

        log.debug(orderItemC.get(2L).getDati().toString());



    }

    @Test
    @Order(5)
    public void orderitemAdd(){
        ProductRequest pr = new ProductRequest("brandAdd","modelAdd","descriptionAdd",25,120D);
        productC.create(pr);
        ProductDTO p = productC.list().getDati().stream().filter(o->o.getBrand().equals("brandAdd") && o.getModel().equals("modelAdd")).findFirst().get();

        Assertions.assertThat(orderItemC.addItemToOrder(null).getRc()).isEqualTo(false);

        OrderItemRequest oir = new OrderItemRequest();
        Assertions.assertThat(orderItemC.addItemToOrder(oir).getRc()).isEqualTo(false);


        oir.setOrderId(11111111111L);
        Assertions.assertThat(orderItemC.addItemToOrder(oir).getRc()).isEqualTo(false);


        oir.setProductId(111111111111111L);
        Assertions.assertThat(orderItemC.addItemToOrder(oir).getRc()).isEqualTo(false);

        oir.setOrderId(1L);
        Assertions.assertThat(orderItemC.addItemToOrder(oir).getRc()).isEqualTo(false);

        oir.setProductId(p.getId());
        oir.setQuantity(999999999);
        Assertions.assertThat(orderItemC.addItemToOrder(oir).getRc()).isEqualTo(false);

        oir.setUnitPrice(250D);
        oir.setQuantity(null);
        Assertions.assertThat(orderItemC.addItemToOrder(oir).getRc()).isEqualTo(true);

        log.debug(orderC.get(1L).getDati().toString());


        oir.setQuantity(null);
        Assertions.assertThat(orderItemC.addItemToOrder(oir).getRc()).isEqualTo(true);

    }

    @Test
    @Order(6)
    public void orderitemRemove(){
        ProductDTO p = productC.list().getDati().stream().filter(o->o.getBrand().equals("brandAdd") && o.getModel().equals("modelAdd")).findFirst().get();

        Assertions.assertThat(orderItemC.addItemToOrder(null).getRc()).isEqualTo(false);

        OrderItemRequest oir = new OrderItemRequest();
        Assertions.assertThat(orderItemC.addItemToOrder(oir).getRc()).isEqualTo(false);


        oir.setOrderId(11111111111L);
        Assertions.assertThat(orderItemC.addItemToOrder(oir).getRc()).isEqualTo(false);


        oir.setProductId(111111111111111L);
        Assertions.assertThat(orderItemC.addItemToOrder(oir).getRc()).isEqualTo(false);

        oir.setOrderId(1L);
        Assertions.assertThat(orderItemC.addItemToOrder(oir).getRc()).isEqualTo(false);

        oir.setProductId(p.getId());
        oir.setQuantity(0);
        Assertions.assertThat(orderItemC.addItemToOrder(oir).getRc()).isEqualTo(false);
    }

}
