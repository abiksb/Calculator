class InfixToPost {
    private String input; // String with Infix form of expression
    private String output = ""; //String with Postfix form of expression
    private Stack stack; //Stack for operators

    //Basic constructor
    //Input: String with Infix form of expression
    InfixToPost(String input) {
        this.input = input;
        stack = new Stack(input.length() ); //Creates a Stack for operators
// Tutaj Stack bedzie mial dlugosc inputa, chociaz wiadomo, ze tyle nie bedzie potrzeba. Jest jakis trick na to?
    }

    //Method converts Infix form to Postfix form of expression
    //Output: String output with Postfix form of expression with whitespaces between its elements
    String convert() {
        // Loop through every character of String input
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i); //Character at i position of input String
            //Switch to determine what to do with character. I
            switch (ch) {
                //Instantly pushes ( on the stack
                case '(':
                    stack.push(ch);
                    break;
                //Clears () from the stack
                case ')':
                    evaluateOperator(ch);
                    break;
                //Evaluates operators based on their Precedence
                case '-':
                    // Check if number is negative
                    //True when '-' is 1st in input or '(' is before
                    if (output.equals("") || input.charAt(i-1) == '('){
                        output += ch;
                        break;
                    }
                case '+':
                case '*':
                case '/':
                    //first evaluate Precedence, then what to do with operator
                    evaluateOperator(ch, evaluatePrecedence(ch) );
                    break;
                default:
                    //for numbers and . add to the output String
                    output += ch;
                    break;
            } //for loop
        } //switch

        //If anything remains on the Stack add it to the output now
        while (!stack.isEmpty()) {
            output += " " + (char) stack.pop();
        }
        //Return expression in Postfix form with whitespaces between its elements
        return output;
    }

    //Method evaluates Precedence of an operator
    //Input: a single operator
    //Output: Precedence. Lower precedence means higher priority
    int evaluatePrecedence(char Operator) {
        int precedence = 0; //Initializes int precedence tutaj czy w kazdym case?
          switch (Operator) {
            case '*':
            case '/':
               precedence = 1;
                break;
            case '-':
            case '+':
               precedence = 2;
                break;
            case '(':
               precedence = Integer.MAX_VALUE; //So it stays on the stack until ) is met
                break;
        }
        return precedence; //returns precedence value w ten sposob czy lepiej przy kazdym case po prostu return?
    }

    //Method evaluates whether to put operator on the Stack or add to output String
    //Input: Operator and its precedence
    void evaluateOperator(char currentOperator, int precedence) {

        //Check if there is something on the stack
        if (!stack.isEmpty()) {
            //Check the last operator on the stack and evaluate its Precedence
            char previousOperator = (char) stack.whatsOnTop();
            int previousPrecedence = evaluatePrecedence(previousOperator);

            //If current Operator has higher priority than one on the stack, put one from the stack into output
            if (precedence >= previousPrecedence) {
                output += " " + (char) stack.pop();
            }

        }
        //Push current operator to the Stack regardless
        stack.push(currentOperator);
        output += " ";
    }

    //@Override
    //Method adds content of Parenthesis to the output String
    //Input: a single ( char
    void evaluateOperator(char rightParenthesis) {
        // Dodac sprawdzenie czy istnieje lewy nawias na stacku!

        // Until ( occurs on the stack add operators from the stack to output String
       while ( (char) stack.whatsOnTop() != '(') {
           output += " " + (char) stack.pop();
       }

        stack.pop(); //get rid of left Parenthisis from the stack
    }
}