package infixtoposix;
/* This code will push the operators in the stack and pop them based on precedence i.e. until the
 * top of the stack has an element of lower precedence When you encounter a bracket ")" then you
 * keep popping until you reach "(" When you reach the end of a String input, then you keep popping
 * the elements until you reach the end of the stack. e.g. if the input is --> 1+2*3/4 then the
 * output should be --> 123*4/+ */

/** @author Bowen Li, Kalyan Kanagala */
public class InToPost<T> {

    private LinkedStack<Character> opStack;
    private String input;
    private String output = "";

    /* Defines an empty stack and initializes input */
    public InToPost(String given) {
	opStack = new LinkedStack();
	input = given;
    }

    /* This is where you will write your main code. */
    public String convertToPost() {

	for (int i = 0; i < input.length(); i++) {
	    char c = input.charAt(i);
	    if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')') {
		checkPrecedence(c);
	    } else {
		output += c;
	    }
	}
	while (!opStack.isEmpty()) {
	    output += opStack.pop();
	}
	return output;
    }

    /* Check for precedence and accordingly use push and pop. This is required for popping elements
     * from the stack. prec1 is 1 for + and - prec2 is for * and / YOU CAN MODIFY THIS METHOD
     * SIGNATURE BY CHANGING THE NUMBER OF INPUT PARAMETERS TOO. */
    public void checkPrecedence(char c) {

	// The parameter prec1 is unnecessary.
	if (opStack.isEmpty() || opStack.peek() == '(') {
	    opStack.push(c);
	} else if (c == '(') {
	    opStack.push(c);
	} else if (c == ')') {
	    while (opStack.peek() != '(')
		output += opStack.pop();
	    opStack.pop();
	} else if ((opStack.peek() == '+' || opStack.peek() == '-') && (c == '*' || c == '/')) {
	    opStack.push(c);
	} else if ((opStack.peek() == '*' || opStack.peek() == '/') && (c == '+' || c == '-')) {
	    output += opStack.pop();
	    checkPrecedence(c);
	} else {
	    output += opStack.pop();
	    opStack.push(c);
	}
    }

    /* This method should be called when you encounter a closing bracket --> ")" You keep popping
     * elements from the stack and concatenating to the output string until you encounter "(" When
     * you encounter "(", then do nothing further. */
    public void checkBrackets() {
	// Unnecessary.
    }
}
