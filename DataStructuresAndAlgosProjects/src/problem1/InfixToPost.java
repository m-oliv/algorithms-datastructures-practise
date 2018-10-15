package problem1;

import datastructures.StackArray;
import exceptions.EmptyException;
import exceptions.OverflowException;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class InfixToPost {
    private boolean verbose;
    private String infixExpression;
    private String postfixExpression;

    public InfixToPost(boolean verbose, String infixExpression) {
        this.verbose = verbose;
        this.infixExpression = infixExpression;
        this.postfixExpression = "";
    }

    public InfixToPost(String infixExpression) {
        this.verbose = false;
        this.infixExpression = infixExpression;
        this.postfixExpression = "";
    }

    /**
     * Retrieve the precedence of a given operator.
     *
     * @param operator the operator
     * @return the precedence of the operator
     */
    private int precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return -1;
        }
    }

    /**
     * Convert the infix expression into a postfix expression.
     *
     * @return the postfix expression
     * @throws OverflowException throw an exception if the string is full
     * @throws EmptyException    throw an exception if the string is empty
     */
    public String convert() throws OverflowException, EmptyException {
        // initialize the string tokenizer
        StringTokenizer st = new StringTokenizer(this.infixExpression);

        // lists of special substrings (operators and parenthesis)
        List<String> operators = Arrays.asList("+", "-", "*", "/", "^");
        List<String> parenthesis = Arrays.asList("(", ")");

        StringBuilder builder = new StringBuilder();
        StackArray<String> stack = new StackArray<>();
        String token;

        while (st.hasMoreTokens()) {
            // put the token in a temporary string
            token = st.nextToken();
            // if the token is a number, append it to the final expression
            if (!operators.contains(token) && !parenthesis.contains(token)) {
                builder.append(token).append(" ");
            } else {
                switch (token) {
                    case "(":
                        // push the opening parenthesis into the stack
                        stack.push(token);

                        // if the verbose flag is active, print the stack contents
                        if (this.verbose) {
                            System.out.println("Stack:");
                            System.out.println(stack.toString());
                        }
                        break;
                    case ")":
                        // append elements from the stack until an opening parenthesis is found
                        while (!stack.empty() && !stack.top().equals("(")) {
                            builder.append(stack.pop()).append(" ");
                        }

                        // remove the first element from the stack
                        stack.pop();

                        // if the verbose flag is active, print the stack contents
                        if (this.verbose) {
                            System.out.println("Stack:");
                            System.out.println(stack.toString());
                        }
                        break;
                    default:
                        // if the token is an operator
                        while (!stack.empty() && precedence(token) <= precedence(stack.top())) {
                            // append the stack element into the final expression
                            builder.append(stack.pop()).append(" ");

                            // if the verbose flag is active, print the stack contents
                            if (this.verbose) {
                                System.out.println("Stack:");
                                System.out.println(stack.toString());
                            }
                        }

                        // push the token into the stack
                        stack.push(token);

                        // if the verbose flag is active, print the stack contents
                        if (this.verbose) {
                            System.out.println("Stack:");
                            System.out.println(stack.toString());
                        }
                        break;
                }
            }
        }

        // get the remaining items from the stack
        while (!stack.empty()) {
            builder.append(stack.pop()).append(" ");

            // if the verbose flag is active, print the stack contents
            if (this.verbose) {
                System.out.println("Stack:");
                System.out.println(stack.toString());
            }
        }

        // get the full postfix expression
        this.postfixExpression = builder.toString();

        return this.postfixExpression;
    }

    /**
     * Returns the postfix expression.
     *
     * @return the postfix expression string
     */
    public String toString() {
        return this.postfixExpression;
    }
}
