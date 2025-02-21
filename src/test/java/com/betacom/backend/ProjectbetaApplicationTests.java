package com.betacom.backend;

/*
import com.betacom.backend.daniel.orderTestSetup;
import com.betacom.backend.daniel.testOrder;

import com.betacom.backend.megatest.FinalProductTest;
*/

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Suite
@SelectClasses({

	/*
		FinalProductTest.class,

		FinalCustomersTest.class
		*/
})
class ProjectbetaApplicationTests {

	@Test
	void contextLoads() {
	}

}
