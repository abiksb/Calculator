package com.example.calculator;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@NoArgsConstructor
@Component
public class InfixToPost {

    //Method converts Infix form to Postfix form of expression
    //Input: String to convert
    //Output: String output with Postfix form of expression with whitespaces between its elements
    public String convert(@NonNull String input) {

        //Create object with Stack and String output
        InfixToPostModel toPostModel = new InfixToPostModel();

        //Set operatorsStack to input length
        Stack operatorsStack = new Stack(input.length() );

        //Pytanie co lepsze w tej metodzie - set i get Stack uzywac. Czy ustawic operatorsStack jako public w modelu
        //i uzywac np toPostmodel.operatorsStack.pop() bezposrednio?
        toPostModel.setStack(operatorsStack);

        // Loop through every character of String input
        for (int i = 0; i < input.length(); i++) {

            operatorsStack = toPostModel.getStack(); //update local operatorsStack
            char currentEquationChar = input.charAt(i); //Character at i position of input String

            //Switch to determine what to do with character.
            switch (currentEquationChar) {
                //Instantly pushes ( on the operatorsStack
                case '(':
                    operatorsStack.push(currentEquationChar);
                    toPostModel.setStack(operatorsStack);
                    break;
                //Clears () from the operatorsStack
                case ')':
                    toPostModel = evaluateOperator(toPostModel);
                    toPostModel.setStack(operatorsStack);
                    break;

                //Evaluates operators based on their Precedence
                case '-':
                    // Check if number is negative
                    // True when '-' is 1st in input or '(' is before
                    if (toPostModel.getOutput().equals("") || input.charAt(i-1) == '('){
                        toPostModel.setOutput(toPostModel.getOutput() + currentEquationChar);
                        break;
                    }
                case '+':
                case '_':
                case '*':
                case '/':
                    //first evaluate Precedence, then what to do with operator
                    toPostModel = evaluateOperator(currentEquationChar, evaluatePrecedence(currentEquationChar), toPostModel);
                    toPostModel.setStack(operatorsStack);
                    break;
                default:
                    //for numbers and . add to the output String
                    toPostModel.setOutput(toPostModel.getOutput() + currentEquationChar);
                    break;
            } //for loop
        } //switch

        operatorsStack = toPostModel.getStack();
        //If anything remains on the Stack add it to the output now
        while (!operatorsStack.isEmpty()) {
            toPostModel.setOutput(toPostModel.getOutput() + " " + (char) operatorsStack.pop() );
        }
        //Return expression in Postfix form with whitespaces between its elements
        return toPostModel.getOutput();
    }

    //Method evaluates Precedence of an operator
    //Input: a single operator
    //Output: Precedence. Lower precedence means higher priority
    private int evaluatePrecedence(char Operator) {
        int precedence = 0; //Initializes int precedence tutaj czy w kazdym case?
          switch (Operator) {
            case '*':
            case '/':
                precedence = 1;
                break;
            case '-':
            case '+':
            case '_':
                precedence = 2;
                break;
            case '(':
                precedence = Integer.MAX_VALUE; //So it stays on the stack until ) is met
                break;
        }
        return precedence; //returns precedence value w ten sposob czy lepiej przy kazdym case po prostu return?
    }

    //Method evaluates whether to put operator on the Stack or add to output String
    //Input: Operator, its precedence, infixToPostModel with operatorsStack and output String
    //Output: infixToPostModel with operatorsStack and output String
    private InfixToPostModel evaluateOperator(char currentOperator, int currentPrecedence, InfixToPostModel infixToPostModel) {

        //Get Stack with current operators
        Stack operatorsStack = infixToPostModel.getStack();

        //Check if there is something on the stack
        if (!operatorsStack.isEmpty()) {
            //Check the last operator on the stack and evaluate its Precedence
            char previousOperator = (char) operatorsStack.whatsOnTop();
            int previousPrecedence = evaluatePrecedence(previousOperator);

            //If current Operator has higher priority than one on the stack, put one from the stack into output
            if (currentPrecedence >= previousPrecedence) {
                //Lepiej takie sety i gety zagmatwane czy na poczatku String osobny stworzyc / publiczny Output w modelu?
                infixToPostModel.setOutput(infixToPostModel.getOutput() + " " + (char) operatorsStack.pop() );
            }
        }
        //Push current operator to the Stack regardless
        operatorsStack.push(currentOperator);

        //Update infixToPostModel before returning it
        infixToPostModel.setStack(operatorsStack);
        infixToPostModel.setOutput(infixToPostModel.getOutput()  + " ");

        return infixToPostModel;
    }

    //@Override
    //Method adds content of Parenthesis to the output String
    //Input: InfixToPostModel object with operatorsStack and output String
    private InfixToPostModel evaluateOperator(InfixToPostModel infixToPostModel) {
        // Dodac sprawdzenie czy istnieje lewy nawias na stacku!

        //Get operatorsStack and output String from Model object
        Stack operatorsStack = infixToPostModel.getStack();
        String output = infixToPostModel.getOutput();

        // Until ( occurs on the operatorsStack add operators from the operatorsStack to output String
        while ( (char) operatorsStack.whatsOnTop() != '(') {
           infixToPostModel.setOutput(output + " " + (char) operatorsStack.pop() );
       }

        //Get rid of left Parenthisis from the operatorsStack
        operatorsStack.pop();

        //update opertorsStack before returning infixToPostModel
        infixToPostModel.setStack(operatorsStack);

        return infixToPostModel;
    }
}