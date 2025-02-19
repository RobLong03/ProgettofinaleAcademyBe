package com.betacom.backend.davideTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.backend.controller.products.MotherboardController;
import com.betacom.backend.dto.products.MotherboardDTO;
import com.betacom.backend.request.products.MotherboardRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.response.ResponseObject;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MotherboardControllerTest {

	@Autowired
	MotherboardController motherbC;
	
	@Test
	@Order(2)
	public void listMotherboardTest() throws Exception {
		
		MotherboardRequest motherb=new MotherboardRequest();		
		motherb.setBrand("MSI");
		motherb.setModel("MAG B660M MORTAR");
		motherb.setDescription("Scheda madre Micro-ATX con supporto DDR4 e PCIe 4.0");
		motherb.setStock(15);
		motherb.setPrice(149.50);
		motherb.setCpuCompatibility("Intel 12th/13th Gen Core");
		
		motherbC.create(motherb);
		
		ResponseList<MotherboardDTO> lMotherb=motherbC.list();		
		Assertions.assertThat(lMotherb.getDati().size()).isEqualTo(2);
	}
	
	@Test
	@Order(3)
	public void getMotherboardTest() throws Exception {
		
		ResponseObject<MotherboardDTO> motherb=motherbC.get(3L);
		
		Assertions.assertThat(motherb.getRc()).isEqualTo(true);
		Assertions.assertThat(motherb.getDati().getBrand()).isEqualTo("ASUS");
		Assertions.assertThat(motherb.getDati().getStock()).isEqualTo(25);
		Assertions.assertThat(motherb.getDati().getPrice()).isEqualTo(199.99);
		
		Assertions.assertThat(motherbC.get(99L).getRc()).isEqualTo(false);
		Assertions.assertThat(motherbC.get(99L).getMsg()).isEqualTo("does-not-exist-get");
	}
	
	@Test
	@Order(1)
	public void createMotherboardTest() throws Exception {
		
		MotherboardRequest motherb=new MotherboardRequest();		
		motherb.setBrand("ASUS");
		motherb.setModel("ROG STRIX B550-F");
		motherb.setDescription("Scheda madre da gaming con PCIe 4.0 e WiFi 6");
		motherb.setStock(25);
		motherb.setPrice(199.99);
		motherb.setCpuCompatibility("AMD Ryzen 5000/3000 Series");
		
		ResponseBase res=motherbC.create(motherb);
		
		Assertions.assertThat(res.getRc()).isEqualTo(true);
		Assertions.assertThat(res.getMsg()).isNull();
			
		motherb.setBrand(null);
		motherb.setModel("ROG STRIX B550-F");
		motherb.setDescription("Scheda madre da gaming con PCIe 4.0 e WiFi 6");
		motherb.setStock(null);
		motherb.setPrice(199.99);
		motherb.setCpuCompatibility("AMD Ryzen 5000/3000 Series");
		
		res=motherbC.create(motherb);
		
		Assertions.assertThat(res.getRc()).isEqualTo(false);
		Assertions.assertThat(res.getMsg()).isEqualTo("missing-attributes-create");
	}
	
	@Test
	@Order(4)
	public void updateMotherboardTest() throws Exception {
		
		MotherboardRequest motherb=new MotherboardRequest();
		motherb.setId(3L);
		motherb.setBrand("ASUS");
		motherb.setModel("ROG STRIX B550-F");
		motherb.setDescription("Scheda madre da gaming con PCIe 4.0 e WiFi 6");
		motherb.setStock(25);
		motherb.setPrice(187.90);
		motherb.setCpuCompatibility("AMD Ryzen 5000/3000 Series");
		
		ResponseBase res=motherbC.update(motherb);
		
		Assertions.assertThat(res.getRc()).isEqualTo(true);
		Assertions.assertThat(motherbC.get(3L).getDati().getPrice()).isEqualTo(187.90);
		
		motherb.setId(null);
		motherb.setBrand("ASUS");
		motherb.setModel(null);
		motherb.setDescription("Scheda madre da gaming con PCIe 4.0 e WiFi 6");
		motherb.setStock(25);
		motherb.setPrice(187.90);
		motherb.setCpuCompatibility("AMD Ryzen 5000/3000 Series");
		
		res=motherbC.update(motherb);
		
		Assertions.assertThat(res.getRc()).isEqualTo(false);
		Assertions.assertThat(res.getMsg()).isEqualTo("missing-id-update");
	}
	
}
