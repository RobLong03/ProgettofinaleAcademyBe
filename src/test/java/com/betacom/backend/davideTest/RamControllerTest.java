package com.betacom.backend.davideTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.backend.controller.products.RamController;
import com.betacom.backend.dto.products.RamDTO;
import com.betacom.backend.request.products.RamRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.response.ResponseObject;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RamControllerTest {

	@Autowired
	RamController ramC;
	
	@Test
	@Order(2)
	public void listRamTest() throws Exception {
		
		RamRequest ram=new RamRequest();
		ram.setBrand("Corsair");
		ram.setModel("Vengeance LPX");
		ram.setDescription("RAM DDR4 ad alte prestazioni per gaming e overclocking");
		ram.setStock(50);
		ram.setPrice(79.99);
		ram.setMhz(3200);
		ram.setSize(16);
		
		ramC.create(ram);
		
		ResponseList<RamDTO> lRam=ramC.list();
		Assertions.assertThat(lRam.getDati().size()).isEqualTo(2);
	}
	
	@Test
	@Order(3)
	public void getRamTest() throws Exception {
		
		ResponseObject<RamDTO> ram=ramC.get(1L);
		
		Assertions.assertThat(ram.getRc()).isEqualTo(true);
		Assertions.assertThat(ram.getDati().getBrand()).isEqualTo("Kingston");
		Assertions.assertThat(ram.getDati().getStock()).isEqualTo(30);
		Assertions.assertThat(ram.getDati().getPrice()).isEqualTo(129.99);
		
		Assertions.assertThat(ramC.get(99L).getRc()).isEqualTo(false);	
		Assertions.assertThat(ramC.get(99L).getMsg()).isEqualTo("does-not-exist-get");
	}
	
	@Test
	@Order(1)
	public void createRamTest() throws Exception {
		
		RamRequest ram=new RamRequest();
		ram.setBrand("Kingston");
		ram.setModel("Fury Beast");
		ram.setDescription("Memoria RAM DDR5 con alte frequenze e bassa latenza");
		ram.setStock(30);
		ram.setPrice(129.99);
		ram.setMhz(6000);
		ram.setSize(32);
		
		ResponseBase res=ramC.create(ram);
		Assertions.assertThat(res.getRc()).isEqualTo(true);
		
		//test without brand
		ram.setBrand(null);
		ram.setModel("Fury Beast");
		ram.setDescription("Memoria RAM DDR5 con alte frequenze e bassa latenza");
		ram.setStock(30);
		ram.setPrice(129.99);
		ram.setMhz(6000);
		ram.setSize(32);
		
		res=ramC.create(ram);
		Assertions.assertThat(res.getRc()).isEqualTo(false);
		Assertions.assertThat(res.getMsg()).isEqualTo("missing-attributes-create");
	}
	
	@Test
	@Order(4)
	public void updateRamTest() throws Exception {
		
		RamRequest ram=new RamRequest();
		ram.setId(2L);
		ram.setBrand("Corsair");
		ram.setModel("Vengeance LPX");
		ram.setDescription("RAM DDR4 ad alte prestazioni per gaming e overclocking");
		ram.setStock(37);
		ram.setPrice(90.00);
		ram.setMhz(3200);
		ram.setSize(16);
		
		ResponseBase res=ramC.update(ram);
		
		Assertions.assertThat(res.getRc()).isEqualTo(true);
		Assertions.assertThat(ramC.get(2L).getDati().getStock()).isEqualTo(37);
		
		ram.setId(99L);
		ram.setBrand("Corsair");
		ram.setModel("Vengeance LPX");
		ram.setDescription("RAM DDR4 ad alte prestazioni per gaming e overclocking");
		ram.setStock(37);
		ram.setPrice(90.00);
		ram.setMhz(3200);
		ram.setSize(16);
		
		res=ramC.update(ram);
		
		Assertions.assertThat(res.getRc()).isEqualTo(false);
		Assertions.assertThat(res.getMsg()).isEqualTo("does-not-exist-update");
	}
	
	@Test
	@Order(5)
	public void deleteRamTest() throws Exception {
		
		Assertions.assertThat(ramC.delete(1L).getRc()).isEqualTo(true);
		Assertions.assertThat(ramC.list().getDati().size()).isEqualTo(1);
		
		Assertions.assertThat(ramC.delete(99L).getRc()).isEqualTo(false);
	}

}
