package problem1;

import datastructures.StackArray;
import exceptions.DivideByZeroException;
import exceptions.EmptyException;
import exceptions.OverflowException;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class EvalPostfix {
    private boolean verbose;
    private int value;

    public EvalPostfix(boolean verbose) {
        this.verbose = verbose;
        this.value = 0;
    }

    public EvalPostfix() {
        this.verbose = false;
        this.value = 0;
    }

    /**
     * Performs the mathematical operation and returns the value.
     *
     * @param operator the operator, for example +
     * @param op1      the first operand
     * @param op2      the second operand
     * @return the value of the calculation
     * @throws DivideByZeroException throws an exception if a division by zero is detected
     */
    public int calculate(String operator, int op1, int op2) throws DivideByZeroException {
        switch (operator) {
            case "+":
                return op1 + op2;
            case "-":
                return op1 - op2;
            case "*":
                return op1 * op2;
            case "/":
                if (op2 == 0) {
                    throw new DivideByZeroException("Cannot divide by zero!");
                }
                return op1 / op2;
            case "^":
                return op1 ^ op2;
        }
        return 0;
    }

    /**
     * Evaluates a postfix expression and returns its value.
     *
     * @param expression the postfix operation
     * @return the value of the expression evaluation
     * @throws OverflowException     throws an exception if the stack is full
     * @throws EmptyException        throws an exception if the stack is empty
     * @throws DivideByZeroException throws an exception if the expression contains a divide by zero operation
     */
    public int evaluate(String expression) throws OverflowException, EmptyException, DivideByZeroException {
        List<String> operators = Arrays.asList("+", "-", "*", "/", "^");
        StackArray<Integer> stackArray = new StackArray<>(expression.length());
        StringTokenizer tokenizer = new StringTokenizer(expression);
        String token;
        int op1, op2;

        while (tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();

            // if the token is an operator, perform the calculation
            if (operators.contains(token)) {
                // place the operands into temps
                op2 = stackArray.pop();
                op1 = stackArray.pop();

                // perform the calculation and save the value on the stack
                stackArray.push(calculate(token, op1, op2));

                // if the verbose flag is active, print the stack contents
                if (this.verbose) {
                    System.out.println("Stack:");
                    System.out.println(stackArray.toString());
                }
            } else {
                // put the value in the stack
                stackArray.push(Integer.parseInt(token));

                // if the verbose flag is active, print the stack contents
                if (this.verbose) {
                    System.out.println("Stack:");
                    System.out.println(stackArray.toString());
                }
            }
        }

        // get the final value of the expression from the stack
        this.value = stackArray.pop();

        // if the verbose flag is active, print the stack contents
        if (this.verbose) {
            System.out.println("Stack:");
            System.out.println(stackArray.toString());
        }

        return this.value;
    }

    /**
     * Print the string that contains the postfix evaluation result.
     *
     * @return the string that contains the postfix evaluation result
     */
    public String toString() {
        return Integer.toString(this.value);
    }
}
