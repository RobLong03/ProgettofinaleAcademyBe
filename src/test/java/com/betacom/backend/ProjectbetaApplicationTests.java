package com.betacom.backend;

import com.betacom.backend.megatest.FinalCustomersTest;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Suite
@SelectClasses({
		FinalCustomersTest.class
})
class ProjectbetaApplicationTests {

	@Test
	void contextLoads() {
	}

}
