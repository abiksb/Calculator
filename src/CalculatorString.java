public class CalculatorString {

    //Method that evaluates expression based on provided String.
    //Input: String with expression where "r" is intended for previous result, double with previous result.
    //Output: double with result of calculations.
    double calculate(String input, double previousResult) {

        //Create an object with InfixToPost for converting Strings
        InfixToPost inToPost = new InfixToPost();

        //Create an InfixToPostModel object
        InfixToPostModel model = new InfixToPostModel();

        //Convert expression String from Infix form to Postfix form with whitespaces between elements
        String inputConverted = inToPost.convert(input, model);

        //Create an object with PostfixParser class for parsing the expression
        PostfixParser postParser = new PostfixParser();

        //Calculates result of expression
        double result = postParser.evaluate(inputConverted, previousResult);

        //Print result
        System.out.println("Result of " + input + " is " + result);
        return result;
    }

    //Method that prints instructions for using calculator
    void show() {
        System.out.println("Available operations: addition, subtraction, multiplication, division.");
        System.out.println("Provide \"r\" inside the expression to insert result of the last calculation.");
        System.out.println("Type \"back\" to go back.");
        System.out.println("Type your calculation:");
    }
}