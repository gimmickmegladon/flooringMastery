package com.example.flooringMastery;

import com.example.flooringMastery.service.FlooringService;
import com.example.flooringMastery.service.FlooringServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FlooringMasteryApplicationTests {

	public FlooringMasteryApplication app;
	public FlooringService fs;
	@Test
	void contextLoads() {
		FlooringMasteryApplication app = new FlooringMasteryApplication();
	}

	void appExists(){
		assertEquals(app.getClass(), FlooringMasteryApplication.class);
	}

}
