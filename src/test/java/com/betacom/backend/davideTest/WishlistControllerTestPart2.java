//package com.betacom.backend.davideTest;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.betacom.backend.controller.wishlist.WishlistController;
//import com.betacom.backend.response.ResponseBase;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@Rollback(false)
//public class WishlistControllerTestPart2 {
//	
//	@Autowired
//	WishlistController wishlC;
//
//	@Test
//	@Order(3)
//	@Transactional //useful for keeping the Hibernate session open (consequence of LAZY fetching)
//	public void emptyWishlistTest() throws Exception {
//				
//		Assertions.assertThat(wishlC.get(1L).getDati().getWishlistItems().size()).isEqualTo(2);
//		
//		ResponseBase res=wishlC.emptyWishlist(1L);
//		
//		Assertions.assertThat(res.getMsg()).isNull();
//		Assertions.assertThat(wishlC.get(1L).getDati().getWishlistItems().size()).isEqualTo(0);
//		
//		res=wishlC.emptyWishlist(99L);
//		
//		Assertions.assertThat(res.getMsg()).isEqualTo("does-not-exist-wishlist-empty");
//	}
//}
