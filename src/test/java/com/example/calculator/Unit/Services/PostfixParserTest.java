package com.example.calculator.Unit.Services;


import com.example.calculator.Services.PostfixParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PostfixParserTest {

    PostfixParser postfixParser;

    @BeforeEach
    void initialize() {
        postfixParser = new PostfixParser();
    }

    @DisplayName("Should Parse and Calculate from Postfix Expression")
    @ParameterizedTest
    @CsvSource({
            "2 1 3 * +, 5",
            "2 1 + 3 *, 9",
            "2 1 3 * -, -1",
            "2 1 - 3 *, 3",
            "2 1 3 / +, 2.3333333333333333",
            "2 1 + 3 /, 1",
            "2 1 3 / -, 1.66666666666666666",
            "2 1 - 3 /, 0.3333333333333333",
            "2 1 * 3 /, 0.6666666666666666",
            "2 1 + 3 -, 0",
            "2 1 3 5 + 7 - * 2 / +, 2.5",
            "2 1 3 5 + 7 - * 2 / +, 2.5"})
    void shouldParseAndCalculateFromPostfixExpression(String expression, double correctResult){
        double previousResult = 0;

        double result = postfixParser.evaluate(expression, previousResult);

        Assertions.assertEquals(correctResult, result);
    }

    @DisplayName("Should Parse and Calculate from Postfix Expression with Previous Result as Second Argument")
    @ParameterizedTest
    @CsvSource({
            "r 2 1 3 5 + 7 - * 2 / + +, 500, 502.5",
            "r 2 1 3 5 + 7 - * 2 / + +, -500, -497.5",
            "r 2 *, 1, 2",
            "r 5 /, 10, 2"
    })
    void shouldParseAndCalculateFromPostfixExpressionWithPreviousResultAdded(String expression, double previousResult,
                                                                             double correctResult){
        double result = postfixParser.evaluate(expression, previousResult);

        Assertions.assertEquals(correctResult, result);

    }

    @DisplayName("Should treat _ as a + operator")
    @Test
    void shouldTreat_AsAPlusOperator(){
        String expression = "2 2 _";
        double previousResult = 0;
        double result;

        result = postfixParser.evaluate(expression, previousResult);

        Assertions.assertEquals(4, result);

    }

    @DisplayName("Should return number")
    @Test
    void shouldReturnNumber(){
        String expression = "152";
        double previousResult = 0;
        double result;

        result = postfixParser.evaluate(expression, previousResult);

        Assertions.assertEquals(152, result);
    }

    @DisplayName("Should throw exception Null on First Argument")
    @Test
    void shouldThrowExceptionNullOnFirstArgument(){
        String expression = null;
        double previousResult = 0;

        Assertions.assertThrows(NullPointerException.class,
                ()-> {postfixParser.evaluate(expression, previousResult);} );
    }

    @DisplayName("Should throw Number Format exception on empty input String")
    @Test
    void shouldThrowExceptionOnEmptyExpression(){
        String expression = "";
        double previousResult = 0;

        Assertions.assertThrows(NumberFormatException.class,
                ()->{postfixParser.evaluate(expression, previousResult);} );
    }
}

