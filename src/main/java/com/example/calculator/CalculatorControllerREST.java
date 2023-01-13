package com.example.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CalculatorControllerREST {

    @Autowired
    private CalculatorString calculatorString;

    private final AtomicLong counter = new AtomicLong();
    //Czy Controller może być stanowy, czy nie powinien?
    private List<Double> previousResultsList = new Vector<>();

    @GetMapping("/calculator/calculateREST")
    public CalculatorRestRepresentation calculate(@RequestParam(value = "expression", defaultValue = "0") String
                                                    expression, @RequestParam(value = "id", defaultValue = "") Integer previousResultId){

        double previousResult = 0;

        if (previousResultId != null && previousResultId != 0) {previousResult = previousResultsList.get(previousResultId - 1);}

        double result = calculatorString.calculate(expression, previousResult);

        previousResultsList.add(result);

        return new CalculatorRestRepresentation(counter.incrementAndGet(), expression, result);

    }


}
