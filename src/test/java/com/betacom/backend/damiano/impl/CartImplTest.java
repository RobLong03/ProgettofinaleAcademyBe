//package com.betacom.backend.damiano.impl;
//
//import java.util.List;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.slf4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//
//import com.betacom.backend.controller.customer.CustomerController;
//import com.betacom.backend.controller.products.ProductController;
//import com.betacom.backend.dto.cart.CartDTO;
//import com.betacom.backend.dto.cart.CartItemDTO;
//import com.betacom.backend.request.cart.CartItemRequest;
//import com.betacom.backend.request.cart.CartRequest;
//import com.betacom.backend.request.customer.CustomerRequest;
//import com.betacom.backend.request.products.ProductRequest;
//import com.betacom.backend.services.interfaces.cart.CartItemServices;
//import com.betacom.backend.services.interfaces.cart.CartServices;
//import com.betacom.backend.services.interfaces.customer.CustomerSevices;
//import com.betacom.backend.services.interfaces.products.CaseServices;
//import com.betacom.backend.services.interfaces.products.ColorServices;
//
//import jakarta.transaction.Transactional;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@Transactional
//public class CartImplTest {
//
//	@Autowired
//	CartServices cartS;
//
//	@Autowired
//	ProductController prodC;
//
//	@Autowired
//	CustomerController customerC;
//
//	@Autowired
//	CustomerSevices cusS;
//
//	@Autowired
//	CaseServices caseS;
//
//	@Autowired
//	ColorServices colS;
//
//	@Autowired
//	CartItemServices ciS;
//
//	@Autowired
//	Logger log;
//
//	@Test
//	@Order(1)
//	@Rollback(value = false)
//	public void preCartTest() throws Exception{
//		CustomerRequest customerReq = new CustomerRequest("Daniel","bostan","bstvld01d02z129s","daniel@bostan.it","password");
//        customerC.create(customerReq);
//
//        customerReq = new CustomerRequest("Roberto","Longo","rblng01d02z012s","roberto@longo.it","password");
//        customerC.create(customerReq);
//
//        customerReq = new CustomerRequest("Davide","Lodedo","dvdldo01z02z02","davide@lodedo.it","password");
//        customerC.create(customerReq);
//
//        customerReq = new CustomerRequest("Damiano","Serini","dmsrn01d02z012a","damiano@serini.it","password");
//        customerC.create(customerReq);
//
//        ProductRequest preq = new ProductRequest("brand b1","model m1","descrizione d1",25,4.5);
//        prodC.create(preq);
//
//        preq = new ProductRequest("brand b2","model m2","descrizione d2",25,5.5);
//        prodC.create(preq);
//
//        preq = new ProductRequest("brand b3","model m3","descrizione d3",15,6.5);
//        prodC.create(preq);
//	}
//
//	@Test
//	@Order(2)
//	@Rollback(value = false)
//	public void createListGetCart() throws Exception {
//		CartRequest cR = new CartRequest();
//		cR.setCustomerId(1L);
//		cartS.create(cR);
//
//		List<CartDTO> lC = cartS.list();
//		Assertions.assertThat(lC.size()).isEqualTo(1);
//
//		CartDTO c = cartS.get(lC.getFirst().getId());
//		Assertions.assertThat(c.getId()).isEqualTo(1L);
//
//		//error wrong customer id in create
//		CartRequest cR2 = new CartRequest();
//		cR2.setCustomerId(10L);
//		Assertions.assertThatThrownBy(() -> cartS.create(cR2))
//								.isInstanceOf(Exception.class)
//								.hasMessageContaining("cart-missing-customer");
//
//		//error id null in get
//		Assertions.assertThatThrownBy(() -> cartS.get(null))
//								.isInstanceOf(Exception.class)
//								.hasMessageContaining("missing-id-get");
//
//		//error wrong cart id
//		Assertions.assertThatThrownBy(() -> cartS.get(10L))
//								.isInstanceOf(Exception.class)
//								.hasMessageContaining("does-not-exist-get");
//	}
//
//	@Test
//	@Order(3)
//	@Rollback(value = false)
//	public void removeAndClearItemsTest() throws Exception{
//		// Recuperiamo il carrello esistente
//	    CartDTO cart = cartS.get(1L);
//
//	    // Creiamo un prodotto e lo aggiungiamo al carrello
//	    CartItemRequest cir = new CartItemRequest(1L, 1L, 1L, 1); // cartId aggiunto
//	    ciS.create(cir);
//
//	    // Verifichiamo che l'elemento sia stato aggiunto
//	    CartItemDTO cid = ciS.get(1L);
//
//	    // Ora rimuoviamo l'elemento
//	    ciS.delete(1L);
//
//	    // Controlliamo che sia stato rimosso
//	    Assertions.assertThatThrownBy(() -> ciS.get(1L))
//	              .isInstanceOf(Exception.class);
//
//	    // Ora testiamo la pulizia completa del carrello
//	    CartItemRequest cir2 = new CartItemRequest(null, cart.getId(), 1L, 1); // cartId aggiunto
//	    ciS.create(cir2);
//	    CartRequest creq = new CartRequest();
//	    creq.setId(1L);
//	    cartS.clear(creq);
//
//	    // Verifichiamo che il carrello sia vuoto
//	    CartDTO cartAfterClear = cartS.get(1L);
//	    Assertions.assertThatThrownBy(() -> ciS.get(cid.getId()))
//        .isInstanceOf(Exception.class);
//	}
//
//	@Test
//	@Order(5)
//	public void deleteCartTest() throws Exception{
//		Long id = 1L;
//		cartS.delete(id);
//		List<CartDTO> lC = cartS.list();
//		Assertions.assertThat(lC.size()).isEqualTo(0);
//
//		//error missing id
//		Assertions.assertThatThrownBy(() -> cartS.delete(null))
//								.isInstanceOf(Exception.class)
//								.hasMessageContaining("missing-id-delete");
//	}
//}
