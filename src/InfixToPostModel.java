public class InfixToPostModel {

    private Stack stack;
    private String output = "";
    //private int Precedence;

    public Stack getStack() {
        return stack;
    }

    public void setStack(Stack stack) {
        this.stack = stack;
    }

    public String getOutput(){
        return output;
    }

    public void setOutput(String output){
        this.output = output;
    }

/*
    public void setPrecedence (int Precedence){
        this.Precedence = Precedence;
    }

    //public int getPrecedence(){
        return Precedence;
    }
*/
}
