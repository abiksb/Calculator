package com.example.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CalculatorControllerREST {

    @Autowired
    private CalculatorString calculatorString;

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/calculator/calculateREST")
    public CalculatorRestRepresentation calculate(@RequestParam(value = "expression", defaultValue = "0") String
                                                    expression, @RequestParam(value = "previousResult", defaultValue = "0") Double previousResult){

        double result = calculatorString.calculate(expression, previousResult);

        return new CalculatorRestRepresentation(counter.incrementAndGet(), expression, result);

    }


}
