package com.example.flooringMastery;

import com.example.flooringMastery.controller.FlooringController;
import com.example.flooringMastery.service.FlooringService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
@SpringBootApplication
public class FlooringMasteryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlooringMasteryApplication.class, args);
		System.out.println("--------- SPRING START ---------");

		ApplicationContext appContext =
				new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		FlooringController controller = appContext.getBean("controller", FlooringController.class);
		controller.startFlooring();

	}
}
