import java.util.Set;
class PostfixParser {
    //Stack stack; //Stack for holding numbers (double) values on it
    //String input; //String with expression in Postfix form and whitespaces between its elements

    /*
    //Basic constructor
    //Input: String with expression in Postfix form and whitespaces between its elements
    PostfixParser(String input){
        this.input = input;
        stack = new Stack(input.length() ); //Stack to hold numbers
    }
*/
    /*
    //Method that splits String input expression with whitespaces into String array
    //Output: String array with expression
    String[] splitString(){
        return input.split(" ");
    }
    */

    //Method parses an expression in Postfix form
    //Input: Array of Strings with expression, double with previous result
    //Output: Double result of parsing
    double evaluate(String input, double previousResult){

        Stack stack = new Stack(input.length() ); //Stack for holding numbers (double) values on it

        //Split by whitespaces String with expression in postfix form to String array
        String [] inputSplit = input.split(" ");

        //Set of Strings with allowed operators
        final Set<String> OPERATORS = Set.of("*", "/", "+", "-");

        double firstOperand; // double to keep value of first operand
        double secondOperand; //double to keep value of second operand
        double result = 0; //return double

        //For each String in input String array.
        //Push numbers to double Stack, evaluate expression if operator appears
        for (String element : inputSplit){
            //If number or "r" (meaning previousResult)
            if (!OPERATORS.contains(element) ) {

                //If "r" push previousResult on the stack
                if (element.equals("r")) {
                    stack.push(previousResult);
                }
                else {
                    //Parse String to double, push it on the Stack
                    stack.push(Double.parseDouble(element));
                }
            }
            //If operator
            else{
                //Get both operands from the stack. Second operand is on the top
                secondOperand = stack.pop();
                firstOperand = stack.pop();

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
                    case "+":
                        result = firstOperand + secondOperand;
                        break;
                    //Subtraction
                    case "-":
                        result = firstOperand - secondOperand;
                        break;
                }//switch

                //Push the result back onto the stack
                stack.push(result);
            }//else
        }//for each

        //If only a number was provided for input parse it as result
        if (result == 0 && !stack.isEmpty() ){
            result = stack.pop();
        }

        return result;
    }

}//class