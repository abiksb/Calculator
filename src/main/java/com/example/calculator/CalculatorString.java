package com.example.calculator;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorString {

    //Create an object with InfixToPost for converting Strings
    @Autowired
    private InfixToPost inToPost;

    //Create an object with PostfixParser class for parsing the expression
    @Autowired
    private PostfixParser postParser;

    //Method that evaluates expression based on provided String.
    //Input: String with expression where "r" is intended for previous result, double with previous result.
    //Output: double with result of calculations.
    public double calculate(@NonNull String input, double previousResult) {

        //Convert expression String from Infix form to Postfix form with whitespaces between elements
        String inputConverted = inToPost.convert(input);

        //Parses and calculates result of expression
        double result = postParser.evaluate(inputConverted, previousResult);

        //Print result of expression
        System.out.println("Result of " + input + " is " + result);
        return result;
    }
}