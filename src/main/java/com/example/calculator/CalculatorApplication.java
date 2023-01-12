package com.example.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalculatorApplication {

	//Tutaj nie moge dac Autowired bo main jest static?
	//@Autowired
	//CalculatorInitializer calculatorInitializer;
	public static void main(String[] args) {
		SpringApplication.run(CalculatorApplication.class, args);

		//CalculatorRestController calculatorRestController = new CalculatorRestController();
		//calculatorRestController.control();

		//CalculatorInitializer calculatorInitializer = new CalculatorInitializer();
		//Starts calculator sequence
		//calculatorInitializer.CalculatorStart();
	}
}
