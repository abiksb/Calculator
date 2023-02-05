package com.example.calculator.Integration.Services;

import com.example.calculator.Services.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorStringTest {

    CalculatorString calculatorString;
    @Autowired
    InfixToPost infixToPost;
    @Autowired
    PostfixParser postfixParser;

    @BeforeEach
    void initialize(){
        calculatorString = new CalculatorString(infixToPost, postfixParser);
    }

    @DisplayName("Should calculate from String expression")
    @Test
    void shouldCalculateFromString(){
        String expression = "1+1";
        double previousResult = 0;
        double result;

        result = calculatorString.calculate(expression, previousResult);

        Assertions.assertEquals(2, result);
    }

    @DisplayName("Should calculate from String expression with previousResult")
    @Test
    void shouldCalculateFromStringExpressionWithPreviousResult() {
        String expression = "2*r+1";
        double previousResult = 4.0;

        double result = calculatorString.calculate(expression, previousResult);

        Assertions.assertEquals(9.0, result);
    }

    @DisplayName("Should throw null exception on argument")
    @Test
    void shouldThrowNullExceptionOnArgument(){
        String expression = null;

        Assertions.assertThrows(NullPointerException.class, ()->{calculatorString.calculate(expression, 0.0); } );
    }

    @DisplayName("Should throw number format exception on empty input String")
    @Test
    void shouldThrowNumberFormatExceptionOnEmptyInputString(){
        String expression = "";
        double previousResult = 0.0;

        Assertions.assertThrows(NumberFormatException.class,
                ()->{postfixParser.evaluate(expression, previousResult);} );
    }
}
