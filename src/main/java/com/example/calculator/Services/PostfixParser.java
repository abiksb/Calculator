package com.example.calculator.Services;

import com.example.calculator.Collections.Stack;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class PostfixParser {

    //Set of Strings with allowed operators
    private static final Set<String> OPERATORS = Set.of("*", "/", "+", "-", "_");

    //Method parses an expression in Postfix form
    //Input: Array of Strings with expression, double with previous result
    //Output: Double result of parsing


    public double evaluate(@NonNull String input, double previousResult){

        Stack numbersStack = new Stack(input.length() ); //Stack for holding numbers (double) values on it

        //Split by whitespaces String with expression in postfix form to String array
        String [] inputSplit = input.split(" ");

        double firstOperand; // double to keep value of first operand
        double secondOperand; //double to keep value of second operand
        double result = 0; //return double

        //For each String in input String array.
        //Push numbers to double Stack, evaluate expression if operator appears
        for (String element : inputSplit){
            //If number or "r" (meaning previousResult)
            if (!OPERATORS.contains(element) ) {

                //If "r" push previousResult on the numbersStack
                if (element.equals("r")) {
                    numbersStack.push(previousResult);
                }
                else {
                    //Parse String to double, push it on the Stack
                    numbersStack.push(Double.parseDouble(element));
                }
            }
            //If operator
            else{
                //Get both operands from the numbersStack. Second operand is on the top
                secondOperand = numbersStack.pop();
                firstOperand = numbersStack.pop();

                //Switch determining operation type
                switch(element) {
                    //Multiplication
                    case "*":
                        result = firstOperand * secondOperand;
                        break;
                    // Division
                    case "/":
                        result = firstOperand / secondOperand;
                        break;
                    //Addition
                    case "_":
                    case "+":
                        result = firstOperand + secondOperand;
                        break;
                    //Subtraction
                    case "-":
                        result = firstOperand - secondOperand;
                        break;
                }//switch

                //Push the result back onto the numbersStack
                numbersStack.push(result);
            }//else
        }//for each

        //If only a number was provided for input parse it as result
        if (result == 0 && !numbersStack.isEmpty() ){
            result = numbersStack.pop();
        }

        return result;
    }

}//class