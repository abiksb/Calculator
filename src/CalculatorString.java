public class CalculatorString {

    //Method that evaluates expression based on provided String.
    //Input: String with expression where "r" is intended for previous result, double with previous result.
    //Output: double with result of calculations.
    double calculate(String input, double previousResult) {

        //Create an object with InfixToPost for converting Strings
        InfixToPost inToPost = new InfixToPost();

        //Convert expression String from Infix form to Postfix form with whitespaces between elements
        String inputConverted = inToPost.convert(input);

        //Create an object with PostfixParser class for parsing the expression
        PostfixParser postParser = new PostfixParser();

        //Parses and calculates result of expression
        double result = postParser.evaluate(inputConverted, previousResult);

        //Print result of expression
        System.out.println("Result of " + input + " is " + result);
        return result;
    }
}