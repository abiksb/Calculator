class Stack {

    //Tutaj wszystko musi byc public/default no nie?
    private int top; //Position of the last item of the stack
    private double[] doubleArray; //array containing Stack items

    //boolean empty; //true when there are no items on the stack
    //int size; //Size of the stack

    //Basic constructor
    //input: size of the Stack
    public Stack(int size) {
        doubleArray = new double[size]; //initialize array according to the size
        top = -1; //meaning Stack is empty when created
    }

    //Method pushes an item on the top of the Stack
    //Input: int item
    public void push(double item) {
        doubleArray[++top] = item; //increase the top position and put the item there
    }

    //Method retrieves the item from the top of the stack
    //Output: item from the top of the stack
    public double pop() {
        return doubleArray[top--]; //retrieve an item from the top, then decrease the top position
    }

    //Method shows item on the top of the stack
    //Output: item on the top of the stack
    public double whatsOnTop() {
        return doubleArray[top];
    }

    //Method shows if the stack is empty
    //Output: empty variable
    public boolean isEmpty() {
        boolean empty = top == -1; //true if there are no items on the stack
        return empty;
    }
}