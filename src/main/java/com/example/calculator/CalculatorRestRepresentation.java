package com.example.calculator;

public class CalculatorRestRepresentation {

    private long id;
    private String inputExpression;
    private double result;

    public CalculatorRestRepresentation(long id, String inputExpression, double result){
        this.id = id;
        this.inputExpression = inputExpression;
        this.result = result;
    }

    public String getInputExpression() {
        return inputExpression;
    }

    public long getId(){
        return id;
    }

    public double getResult(){
        return result;
    }


}
