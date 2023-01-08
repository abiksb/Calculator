import java.util.Scanner; //Scanner for input streaming
public class CalculatorInitializer {

    //Method initializes the calculator sequence
    public void CalculatorStart(){

        //Create object for input stream
        Scanner input = new Scanner(System.in);

        //Creates objects for basic calculator and for calculator based on strings
        CalculatorBasic calculatorBasic = new CalculatorBasic();
        CalculatorString calculatorString = new CalculatorString();

        //Czy nazwac zmienna previousResult, zeby bylo jasniej? Pozniej ejst metoda result = ...(..., result)
        double result = 0; //double for holding result value
        int command = 0; //int for operating the user decision switch

        //Until user chooses exit
        while (command != 3){

            //Show user his options
            showOptions();
            //Take user's input with decision
            command = input.nextInt();

            //Switch based on user decision
            switch (command) {
                case 1: //Basic calculator

                    //Show user possible operations
                    showBasicOperationTypes();

                    //Take input from user regarding operation for basic calculator
                    int calcBscCommand = input.nextInt();

                    //If user specified "go back", break from switch
                    if (calcBscCommand == 5){
                        break;
                    }
                    //Initialize basic calculator
                    result = calculatorBasic.calculate(result, calcBscCommand);

                    //Ask user for his next decision
                    System.out.println("What to do next?");
                    break;
                case 2: //String calculator

                    //Show user instructions on how to use calculator
                    showStringCalcInfo();

                    //If users specifies "back", break from switch
                    String expression = input.next();
                    if (expression.equals("back") ) {
                        break;
                    }

                    //Initialize calculator based on Strings
                    result = calculatorString.calculate(expression, result);

                    //Ask user for his next decision
                    System.out.println("What to do next:");
                    break;
                case 3: //exit
                    break;
                default: //invalid input
                    System.out.println("Invalid input. Try again.");
                    break;
            }//switch
        }//while

            //Print farewell
            System.out.println("Thank you for using our application");
            System.out.print("Exiting.");

            //Close input stream
            input.close();
        } //CalculatorStart() method

    //Method prints choices that user has regarding calculator types
    private void showOptions() {
        System.out.println("Choose an option:");
        System.out.println("1 - Basic Calculator - one operation, two numbers.");
        System.out.println("2 - Calculator based on String input.");
        System.out.println("3 - Exit.");
    }

    //Method prints choices that user has regarding operation types
    private void showBasicOperationTypes(){
        System.out.println("Choose an operation:");
        System.out.println("1 - addition;");
        System.out.println("2 - subtraction;");
        System.out.println("3 - multiplication;");
        System.out.println("4 - division;");
        System.out.println("5 - go back.");
    }

    //Method that prints instructions for using calculator
    private void showStringCalcInfo() {
        System.out.println("Available operations: addition, subtraction, multiplication, division.");
        System.out.println("Provide \"r\" inside the expression to insert result of the last calculation.");
        System.out.println("Type \"back\" to go back.");
        System.out.println("Type your calculation:");
    }

}

