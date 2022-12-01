import java.util.Scanner;

class CalculatorBasic {

    static Scanner input = new Scanner(System.in); // initialize console read-in
        static double num1 = 0, num2 = 0; // used for calculation
    // dlaczego potrzeba zainicjalizowac, skoro nie jest pewne, czy zostanie wykorzystane
    static double result = 0; //result of calculations

    static void show() {
        System.out.println("Basic calculator.");
        System.out.println("Choose an operation:");
        System.out.println("1 - addition;");
        System.out.println("2 - subtraction;");
        System.out.println("3 - multiplication;");
        System.out.println("4 - division;");
        System.out.println("5 - exit.");
        calculate(num1, num2);
    }

    static double calculate(double num1, double num2) {

        double result = 0;

        int command = input.nextInt(); // exception - different from int

        //Enter num1 and num2 only if proper operation is chosen
        if (command >= 1 && command < 5) {
            System.out.println("Enter your first number");
            num1 = input.nextDouble();

            System.out.println("Enter your second number");
            num2 = input.nextDouble();
        }

        switch (command) {
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
            case 5: //exit app
                exit();
                return result; // Czy to oki czy to kod spagetti?
            default: //reload from the start
                System.out.println("Invalid input. Try again");
                show();
                return result;
        }
        CalculatorBasic2ndUp.show(); //Choose what to do with the results
        return result;
    }

    static void exit(){
        System.out.println("Thank you for using our application");
        System.out.print("Exiting.");
        input.close(); //Closes stream in
    }
}
class CalculatorBasic2ndUp extends CalculatorBasic {
    static void show() {
        System.out.println("What do you want to do next?\nChoose an option:");
        System.out.println("1 - Continue calculation using result as num1;");
        System.out.println("2 - Continue calculation using result as num2;");
        System.out.println("3 - start new calculation;");
        System.out.println("4 - exit.");
        choose();
    }
    static void choose() {

        int command = input.nextInt();

        switch (command) {
            case 1: //num1 as result
                num1 = result;
                CalculatorBasic.show();
                break;
            case 2: //num2 as result
                num2 = result;
                CalculatorBasic.show();
                break;
            case 3: //start calculations anew
                CalculatorBasic.show();
                break;
            case 4: //exit
                exit();
                return;
            default: //Invalit input
                System.out.println("Invalid input. Try again");
                show();
                break;
        }
    }
}