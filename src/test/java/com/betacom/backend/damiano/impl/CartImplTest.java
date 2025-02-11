package com.betacom.backend.damiano.impl;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.betacom.backend.dto.cart.CartDTO;
import com.betacom.backend.request.cart.CartRequest;
import com.betacom.backend.request.customer.CustomerRequest;
import com.betacom.backend.services.interfaces.cart.CartItemServices;
import com.betacom.backend.services.interfaces.cart.CartServices;
import com.betacom.backend.services.interfaces.customer.CustomerSevices;
import com.betacom.backend.services.interfaces.products.CaseServices;
import com.betacom.backend.services.interfaces.products.ColorServices;

import jakarta.transaction.Transactional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
public class CartImplTest {

	@Autowired
	CartServices cartS;
	
	@Autowired
	CustomerSevices cusS;
	
	@Autowired
	CaseServices caseS;
	
	@Autowired
	ColorServices colS;
	
	@Autowired
	CartItemServices ciS;
	
	@Autowired
	Logger log;
	
	@Test
	@Order(1)
	@Rollback(value = false)
	public void createCart() throws Exception {
		CustomerRequest custR = new CustomerRequest(1L, "Dami", "Ser", "SRNCMD97H19A662J", "ser@gg.co", "pass");
		cusS.create(custR);
		CartRequest cR = new CartRequest(0.0, 1L);
		cartS.create(cR);
		
		List<CartDTO> lC = cartS.list();
		Assertions.assertThat(lC.size()).isEqualTo(1);
		
		CartDTO c = cartS.get(lC.getFirst().getId());
		Assertions.assertThat(c.getId()).isEqualTo(1L);
		
	}
}
