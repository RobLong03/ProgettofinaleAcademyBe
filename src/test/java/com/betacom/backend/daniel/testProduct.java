package com.betacom.backend.daniel;

import com.betacom.backend.controller.products.ProductController;
import com.betacom.backend.dto.products.ProductDTO;
import com.betacom.backend.request.products.ProductRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testProduct {



    @Autowired
    ProductController prodC;

    @Order(1)
    @Test
    public void testListVuoti(){
        List<ProductDTO> prodList = prodC.list().getDati();
        Assertions.assertThat(prodList).isNotNull();
        Assertions.assertThat(prodList.size()).isEqualTo(0);

        Assertions.assertThat(prodC.get(null).getRc()).isEqualTo(false);
        Assertions.assertThat(prodC.get(1L).getRc()).isEqualTo(false);
    }

    @Order(2)
    @Test
    public void testCreateVuoti(){
        Assertions.assertThat(prodC.create(null).getRc()).isEqualTo(false);
        ProductRequest preq = new ProductRequest();
        Assertions.assertThat(prodC.create(preq).getRc()).isEqualTo(false);

        preq.setDescription("");
        Assertions.assertThat(prodC.create(preq).getRc()).isEqualTo(false);

        preq.setDescription("a generic description");
        Assertions.assertThat(prodC.create(preq).getRc()).isEqualTo(false);

        preq.setBrand("");
        Assertions.assertThat(prodC.create(preq).getRc()).isEqualTo(false);

        preq.setBrand("a brand");
        Assertions.assertThat(prodC.create(preq).getRc()).isEqualTo(false);

        preq.setModel("");
        Assertions.assertThat(prodC.create(preq).getRc()).isEqualTo(false);

        preq.setModel("a model");
        Assertions.assertThat(prodC.create(preq).getRc()).isEqualTo(false);

        preq.setPrice(0.0);
        Assertions.assertThat(prodC.create(preq).getRc()).isEqualTo(false);

        preq.setPrice(1.0);
        preq.setStock(15);
        Assertions.assertThat(prodC.create(preq).getRc()).isEqualTo(true);

        preq = new ProductRequest("aa","bb","cc",14,2.2);
        Assertions.assertThat(prodC.create(preq).getRc()).isEqualTo(true);

    }

    @Test
    @Order(3)
    public void testList(){
        List<ProductDTO> prodList = prodC.list().getDati();
        Assertions.assertThat(prodList).isNotNull();
        Assertions.assertThat(prodList.size()).isEqualTo(2);

        ProductDTO p1 = prodC.get(1L).getDati();
        Assertions.assertThat(prodC.get(1L).getRc()).isEqualTo(true);

        Assertions.assertThat(p1.getBrand()).isEqualTo("a brand");
        Assertions.assertThat(p1.getModel()).isEqualTo("a model");
        Assertions.assertThat(p1.getPrice()).isEqualTo(1.0);
        Assertions.assertThat(p1.getStock()).isEqualTo(15);
        Assertions.assertThat(p1.getDescription()).isEqualTo("a generic description");

    }

    @Test
    @Order(4)
    public void testUpdate(){
        Assertions.assertThat(prodC.update(null).getRc()).isEqualTo(false);
        ProductRequest preq = new ProductRequest("aaaa","bbbbb","ccccc",15,2.3);

        Assertions.assertThat(prodC.update(preq).getRc()).isEqualTo(false);

        preq.setId(15L);
        Assertions.assertThat(prodC.update(preq).getRc()).isEqualTo(false);

        preq.setId(2L);
        Assertions.assertThat(prodC.update(preq).getRc()).isEqualTo(true);

        ProductDTO p1 = prodC.get(2L).getDati();
        Assertions.assertThat(p1.getBrand()).isEqualTo("aaaa");
        Assertions.assertThat(p1.getModel()).isEqualTo("bbbbb");
        Assertions.assertThat(p1.getPrice()).isEqualTo(2.3);
        Assertions.assertThat(p1.getStock()).isEqualTo(15);
        Assertions.assertThat(p1.getDescription()).isEqualTo("ccccc");

    }

    @Test
    @Order(5)
    public void testDelete(){
        Assertions.assertThat(prodC.delete(null).getRc()).isEqualTo(false);

        Assertions.assertThat(prodC.delete(2L).getRc()).isEqualTo(true);

        Assertions.assertThat(prodC.get(2L).getRc()).isEqualTo(false);

        Assertions.assertThat(prodC.get(1L).getRc()).isEqualTo(true);

        Assertions.assertThat(prodC.list().getDati().size()).isEqualTo(1);
    }


}
