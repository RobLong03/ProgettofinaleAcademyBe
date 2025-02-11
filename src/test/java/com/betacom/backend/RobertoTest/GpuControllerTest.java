package com.betacom.backend.RobertoTest;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.backend.controller.products.GpuController;
import com.betacom.backend.dto.products.GpuDTO;
import com.betacom.backend.request.products.GpuRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GpuControllerTest {

	@Autowired
	GpuController gpuC;
	
	@Autowired
	Logger log;
	
	@Test
	@Order(1)
	public void createGpuTest() throws Exception {
	    GpuRequest gpuRequest = new GpuRequest();
	    gpuRequest.setBrand("NVIDIA");
	    gpuRequest.setModel("RTX 3080");
	    gpuRequest.setDescription("Scheda grafica di alta performance");
	    gpuRequest.setStock(100);
	    gpuRequest.setPrice(699.99);
	    gpuRequest.setGhz(10D); // 10 GB di memoria
	    gpuRequest.setVram(10);
	    // Test di creazione valida
	    ResponseBase response = gpuC.create(gpuRequest);
	    Assertions.assertThat(response.getRc()).isEqualTo(true);
	    
	    // Test di creazione con attributi mancanti (brand)
	    gpuRequest.setBrand(null);  // Simuliamo la mancanza del brand
	    gpuRequest.setModel("RTX 3080");
	    gpuRequest.setDescription("Scheda grafica di alta performance");
	    gpuRequest.setStock(100);
	    gpuRequest.setPrice(699.99);
	    gpuRequest.setVram(12);
	    gpuRequest.setGhz(10D);

	    response = gpuC.create(gpuRequest);
	    Assertions.assertThat(response.getRc()).isEqualTo(false);
	    Assertions.assertThat(response.getMsg()).isEqualTo("missing-attributes-create");
	}
	
	
	@Test
	@Order(2)
	public void listGpuTest() throws Exception {
	    // Test di recupero della lista delle GPU
	    ResponseList<GpuDTO> response = gpuC.list();
	    
	 
	    
	    Assertions.assertThat(response.getRc()).isEqualTo(true);
	    Assertions.assertThat(response.getDati()).isNotEmpty(); // Verifica che ci siano GPU nella lista
	    
	}

	
	@Test
	@Order(3)
	public void updateGpuTest() throws Exception {
		GpuRequest gpuRequest = new GpuRequest();
		gpuRequest.setId(2L);
		gpuRequest.setBrand("EVGA");
		gpuRequest.setModel("SuperNOVA 750W");
		gpuRequest.setDescription("Alimentatore modulare");
		gpuRequest.setStock(100);
	    gpuRequest.setPrice(129.99);
	    gpuRequest.setVram(750);
	    gpuRequest.setGhz(750D);
	    // Test di aggiornamento
	    ResponseBase response = gpuC.update(gpuRequest);
	    log.debug("gpuRequest"+gpuRequest);
	    
	    Assertions.assertThat(response.getRc()).isEqualTo(true);
	    
	    // Test di aggiornamento con attributi mancanti (descrizione)
	    gpuRequest.setDescription(null);  // Simuliamo un'assenza di descrizione
	    
	    response = gpuC.update(gpuRequest);
	    Assertions.assertThat(response.getRc()).isEqualTo(false);
	}



}
