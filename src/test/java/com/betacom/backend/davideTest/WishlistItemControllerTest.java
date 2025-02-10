package com.betacom.backend.davideTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.backend.controller.wishlist.WishlistItemController;
import com.betacom.backend.dto.wishlist.WishlistItemDTO;
import com.betacom.backend.request.products.RamRequest;
import com.betacom.backend.request.wishlist.WishlistItemRequest;
import com.betacom.backend.response.ResponseBase;
import com.betacom.backend.response.ResponseList;
import com.betacom.backend.response.ResponseObject;
import com.betacom.backend.services.implementations.products.RamImpl;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WishlistItemControllerTest {
	
	@Autowired
	WishlistItemController wishlIC;
	
	@Autowired
	RamImpl ramI;

	@Test
	@Order(1)
	public void createWishlistItemTest() throws Exception {
		
		initializateData();
		
		WishlistItemRequest wI=new WishlistItemRequest();
		//wishlistItem customer with id 1
		wI.setProductId(2L);
		ResponseBase res=wishlIC.create(wI, 1L);
		Assertions.assertThat(res.getRc()).isEqualTo(true);
		
		wI.setProductId(7L);
		res=wishlIC.create(wI, 1L);
		Assertions.assertThat(res.getRc()).isEqualTo(true);
		
		wI.setProductId(6L);
		res=wishlIC.create(wI, 1L);
		Assertions.assertThat(res.getMsg()).isNull();
		
		//wishlistItem customer with id 2
		wI.setProductId(5L);
		res=wishlIC.create(wI, 2L);
		Assertions.assertThat(res.getRc()).isEqualTo(true);
		
		wI.setProductId(7L);
		res=wishlIC.create(wI, 2L);
		Assertions.assertThat(res.getRc()).isEqualTo(true);
		
		//wishlistItem customer with id 3
		wI.setProductId(6L);
		res=wishlIC.create(wI, 3L);
		Assertions.assertThat(res.getMsg()).isNull();
		
		wI.setProductId(7L);
		res=wishlIC.create(wI, 3L);
		Assertions.assertThat(res.getMsg()).isNull();
		
		
		wI.setProductId(99L);
		res=wishlIC.create(wI, 2L);
		Assertions.assertThat(res.getMsg()).isEqualTo("missing-product-wishlistItem-create");
		
		wI.setProductId(2L);
		res=wishlIC.create(wI, 99L);
		Assertions.assertThat(res.getMsg()).isEqualTo("missing-customer-wishlistItem-create");
	}
	
	@Test
	@Order(2)
	public void listWishlistItemTest() throws Exception {
		
		ResponseList<WishlistItemDTO> lW=wishlIC.list();
		
		Assertions.assertThat(lW.getRc()).isEqualTo(true);
		Assertions.assertThat(lW.getDati().size()).isEqualTo(7);
	}
	
	@Test
	@Order(3)
	public void getWishlistItemTest() throws Exception {
		
		ResponseObject<WishlistItemDTO> w=wishlIC.get(7L);
		
		Assertions.assertThat(w.getMsg()).isNull();
		Assertions.assertThat(w.getDati().getProduct().getId()).isEqualTo(7);
		
		w=wishlIC.get(99L);
		Assertions.assertThat(w.getMsg()).isEqualTo("does-not-exist-get");
	}
	
	@Test
	@Order(4)
	public void deleteWishlistItemTest() throws Exception {
		
		ResponseBase res=wishlIC.delete(2L);
		
		Assertions.assertThat(res.getMsg()).isNull();
		Assertions.assertThat(wishlIC.list().getDati().size()).isEqualTo(6);
		
		res=wishlIC.delete(99L);
		Assertions.assertThat(res.getMsg()).isEqualTo("does-not-exist-delete");
	}
	
	private void initializateData() throws Exception {
		
		RamRequest ram=new RamRequest();
		ram.setBrand("Corsair");
		ram.setModel("Vengeance RGB Pro");
		ram.setDescription("32GB (2x16GB) DDR5 RAM, 6000MHz, CL36, XMP 3.0, RGB");
		ram.setStock(25);
		ram.setPrice(129.99);
		ram.setMhz(6000);
		ram.setSize(32);
		
		ramI.create(ram);
		
		ram.setBrand("G.Skill");
		ram.setModel("Trident Z5");
		ram.setDescription("32GB (2x16GB) DDR5 RAM, 6000MHz, CL30, XMP 3.0, RGB");
		ram.setStock(50);
		ram.setPrice(149.99);
		ram.setMhz(6400);
		ram.setSize(64);
		
		ramI.create(ram);
		
		ram.setBrand("Kingston");
		ram.setModel("FURY Beast");
		ram.setDescription("32GB (2x16GB) DDR5 RAM, 6000MHz, CL38, Intel XMP, Black");
		ram.setStock(30);
		ram.setPrice(139.99);
		ram.setMhz(6200);
		ram.setSize(32);
		
		ramI.create(ram);
	}
}
