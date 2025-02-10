package com.betacom.backend.RobertoTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.backend.controller.products.PsuController;
import com.betacom.backend.dto.products.PsuDTO;
import com.betacom.backend.request.products.PsuRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PsuControllerTest {
	
	@Autowired
	PsuController psuC;
	
	@Test
	@Order(1)
	public void createPsuTest() throws Exception {
	    PsuRequest psuRequest = new PsuRequest();
	    psuRequest.setBrand("Corsair");
	    psuRequest.setModel("RM850x");
	    psuRequest.setDescription("Alimentatore di alta qualità");
	    psuRequest.setStock(50);
	    psuRequest.setPrice(139.99);
	    psuRequest.setWatt(850);

	    // Test di creazione valida
	    ResponseBase response = psuC.create(psuRequest);
	    Assertions.assertThat(response.getRc()).isEqualTo(true);
	    
	    // Test di creazione con attributi mancanti (brand)
	    psuRequest.setBrand(null);
	    psuRequest.setModel("RM850x");
	    psuRequest.setDescription("Alimentatore di alta qualità");
	    psuRequest.setStock(50);
	    psuRequest.setPrice(139.99);
	    psuRequest.setWatt(850);
	    
	    response = psuC.create(psuRequest);
	    Assertions.assertThat(response.getRc()).isEqualTo(false);
	    Assertions.assertThat(response.getMsg()).isEqualTo("missing-attributes-create");
	}
	@Test
	@Order(2)
	public void listPsuTest() throws Exception {
	    // Test di recupero della lista dei PSU
	    ResponseList<PsuDTO> response = psuC.list();
	    
	    Assertions.assertThat(response.getRc()).isEqualTo(true);
	    Assertions.assertThat(response.getDati()).isNotEmpty(); // Verifica che ci siano PSU nella lista
	}
	@Test
	@Order(3)
	public void updatePsuTest() throws Exception {
	    PsuRequest psuRequest = new PsuRequest();
	    psuRequest.setId(1L);
	    psuRequest.setBrand("EVGA");
	    psuRequest.setModel("SuperNOVA 750W");
	    psuRequest.setDescription("Alimentatore modulare");
	    psuRequest.setStock(100);
	    psuRequest.setPrice(129.99);
	    psuRequest.setWatt(750);
	    
	    // Test di aggiornamento
	    ResponseBase response = psuC.update(psuRequest);
	    Assertions.assertThat(response.getRc()).isEqualTo(true);
	    
	    // Test di aggiornamento con attributi mancanti (descrizione)
	    psuRequest.setDescription(null);  // Simuliamo un'assenza di descrizione
	    
	    response = psuC.update(psuRequest);
	    Assertions.assertThat(response.getRc()).isEqualTo(false);
	  
	}
	




	
}
