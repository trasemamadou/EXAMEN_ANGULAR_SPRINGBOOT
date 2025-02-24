package com.groupeisi.ms_gestschool.ms_gestschool;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootApplication
class MsGestschoolApplicationTests {
	@Test
	void contextLoads() {
		assertTrue(true);
		assertEquals(2, 1+1);
	}
}