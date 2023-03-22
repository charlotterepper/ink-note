package com.charlotte.inknote;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InknoteApplicationTests {

	static {
		System.setProperty("PSQL_USERNAME", "charlotte");
		System.setProperty("PSQL_PASSWORD", "databasexyz");
	}

	@Test
	void contextLoads() {
	}

}
