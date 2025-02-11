package com.betacom.backend.davideTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.betacom.backend.controller.wishlist.WishlistController;
import com.betacom.backend.dto.wishlist.WishlistDTO;
//import com.betacom.backend.model.customer.Customer;
//import com.betacom.backend.model.wishlist.Wishlist;
//import com.betacom.backend.repositories.wishlist.IWishlistRepository;
import com.betacom.backend.request.customer.CustomerRequest;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.response.ResponseObject;
import com.betacom.backend.services.implementations.customer.CustomerImpl;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback(false)
public class WishlistControllerTestPart1 {
	
	@Autowired
	WishlistController wishlC;
	
	@Autowired
	CustomerImpl cusI;
	
//	@Autowired
//	IWishlistRepository wRep;

	@Test
	@Order(1)
	public void listWishlistTest() throws Exception {
		
		initializateData();
		
		//Rimuovere questa parte all'aggiunta delle modifiche di customer
//		Wishlist w1=new Wishlist();
//		w1.setCustomer(new Customer(1L, "Luca", "Rossi", "A1B2C3D4E5F6G7H8", "luca.rossi@example.com", "P@ssw0rd123", null));
//		this.createWishlist(w1);
//		
//		Wishlist w2=new Wishlist();
//		w2.setCustomer(new Customer(2L, "Giulia", "Bianchi", "Z9Y8X7W6V5U4T3S2", "giulia.bianchi@example.com", "Secure!Pass456", null));
//		this.createWishlist(w2);
//		
//		Wishlist w3=new Wishlist();
//		w3.setCustomer(new Customer(3L, "Marco", "Verdi", "M1N2B3V4C5X6Z7L8", "marco.verdi@example.com", "Strong#Pass789", null));
//		this.createWishlist(w3);
		
		
		ResponseList<WishlistDTO> lW=wishlC.list();
		
		Assertions.assertThat(lW.getDati().size()).isEqualTo(3);	
	}
	
	@Test
	@Order(2)
	public void getWihlistTest() throws Exception {
		
		ResponseObject<WishlistDTO> wObj=wishlC.get(1L);
		
		Assertions.assertThat(wObj.getRc()).isEqualTo(true);
		Assertions.assertThat(wObj.getDati().getCustomer().getTaxId()).isEqualTo("A1B2C3D4E5F6G7H8");
		
		wObj=wishlC.get(99L);
		
		Assertions.assertThat(wObj.getRc()).isEqualTo(false);
		Assertions.assertThat(wObj.getMsg()).isEqualTo("does-not-exist-get");
	}
	
	private void initializateData() throws Exception {
		
		CustomerRequest c=new CustomerRequest();
		c.setName("Luca");
		c.setSurname("Rossi");
		c.setTaxId("A1B2C3D4E5F6G7H8");
		c.setEmail("luca.rossi@example.com");
		c.setPassword("P@ssw0rd123");
		
		cusI.create(c);
		
		c.setName("Giulia");
		c.setSurname("Bianchi");
		c.setTaxId("Z9Y8X7W6V5U4T3S2");
		c.setEmail("giulia.bianchi@example.com");
		c.setPassword("Secure!Pass456");
		
		cusI.create(c);
		
		c.setName("Marco");
		c.setSurname("Verdi");
		c.setTaxId("M1N2B3V4C5X6Z7L8");
		c.setEmail("marco.verdi@example.com");
		c.setPassword("Strong#Pass789");
		
		cusI.create(c);
	}
	
	//Rimuovere questa parte all'aggiunta delle modifiche di customer
//	private void createWishlist(Wishlist w) throws Exception {
//		
//		wRep.save(w);
//	}
}
