import java.util.Scanner; //Scanner for input streaming
class CalculatorBasic {

    //Method  used for calculating based on supplied type of operation and user input.
    //Input: previous result of calculations, type of operation
    //Output: result of current calculations.
    public double calculate(double previousResult, int operationType){

        double num1; //for holding first number
        double num2; //for holding second number

        double result = 0; //for holding resulting value

        Scanner input = new Scanner(System.in); // initialize console read-in

        //Enter num1 and num2 only if proper operation is chosen
        if (operationType >= 1 && operationType < 5) {

            //Print instructions
            System.out.println("Enter your first number. Type \"r\" to use previous result.");

            //Take input in string form from user
            String strNum1 = input.next();

            //If previous result was specified by user
            if (strNum1.equals("r") ) {
                num1 = previousResult;
            }
            //Else parse the string number to double
            else {
                num1 = Double.parseDouble(strNum1); //dodac try na input
            }

            //Print instructions
            System.out.println("Enter your second number. Type \"r\" to use previous result.");

            //Take input in string form from user
            String strNum2 = input.next();

            //If previous result was specified by user
            if (strNum2.equals("r") ) {
                num2 = previousResult;
            }
            //Else parse the string number to double
            else {
                num2 = Double.parseDouble(strNum2); //dodac try na input
            }

            //Use evaluate method for obtaining result
            result = evaluate(num1, num2, operationType);

        }
        return result;
    }

    //Method for evaluation of results
    //Input: 1st number, 2nd number, operation type
    //Output: double with result value
    private double evaluate(double num1, double num2, int operationType) {

        double result= 0;

        switch (operationType) {
            case 1: //addition
                result = num1 + num2;
                System.out.println(num1 + " + " + num2 + " = " + result);
                break;
            case 2: //subtraction
                result = num1 - num2;
                System.out.println(num1 + " - " + num2 + " = " + result);
                break;
            case 3: //multiplication
                result = num1 * num2;
                System.out.println(num1 + " * " + num2 + " = " + result);
                break;
            case 4: //division
                result = num1 / num2;
                System.out.println(num1 + " / " + num2 + " = " + result);
                break;
        }
        return result;
    }
}
