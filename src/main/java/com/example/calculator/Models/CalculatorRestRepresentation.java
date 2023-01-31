package com.example.calculator.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CalculatorRestRepresentation {

    private long id;
    private String inputExpression;
    private double result;

}
