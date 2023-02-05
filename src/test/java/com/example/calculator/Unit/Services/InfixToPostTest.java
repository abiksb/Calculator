package com.example.calculator.Unit.Services;

import com.example.calculator.Services.InfixToPost;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class InfixToPostTest {

    InfixToPost infixToPost;

    @BeforeEach
    void initialize(){
        infixToPost = new InfixToPost();
    }


    @DisplayName("Should convert from Infix expression form to Postfix expression form with spaces between elements")
    @ParameterizedTest
    @CsvSource({
            "2+1*3, 2 1 3 * +",
            "(2+1)*3, 2 1 + 3 *",
            "2-1*3, 2 1 3 * -",
            "(2-1)*3, 2 1 - 3 *",
            "2+1/3, 2 1 3 / +",
            "(2+1)/3, 2 1 + 3 /",
            "2-1/3, 2 1 3 / -",
            "(2-1)/3, 2 1 - 3 /",
            "2*1/3, 2 1 * 3 /",
            "2+1-3, 2 1 + 3 -",
            "2+1*(3+5-7)/2, 2 1 3 5 + 7 - * 2 / +",
            "(2+1*(3+5-7)/2), 2 1 3 5 + 7 - * 2 / +"})
    void shouldConvertFromInfixFormToPostfixExpressionForm(String input, String correctOutput){
        String postfixForm;

        postfixForm = infixToPost.convert(input);

        Assertions.assertEquals(correctOutput, postfixForm);
    }


    @DisplayName("Should return negative number")
    @Test
    void shouldReturnNegativeNumber(){
        String input = "-1";
        String postfixForm;

        postfixForm = infixToPost.convert(input);

        Assertions.assertEquals("-1", postfixForm);
    }

    @DisplayName("Should treat _ as operator")
    @Test
    void shouldTreatUnderscoreAsOperator(){
        String input = "1_1";
        String postfixForm;

        postfixForm = infixToPost.convert(input);

        Assertions.assertEquals("1 1 _", postfixForm);
    }

    @DisplayName("Should throw null exception on argument")
    @Test
    void shouldThrowNullExceptionOnArgument(){
        String input = null;

        Assertions.assertThrows(NullPointerException.class, ()->{infixToPost.convert(input); } );
    }

    @DisplayName("Should throw number format exception on empty input String")
    @Test
    void shouldThrowNumberFormatExceptionOnEmptyInputString(){
        String input = "";
        String output;

        output = infixToPost.convert(input);

        Assertions.assertEquals("", output);
    }
}
