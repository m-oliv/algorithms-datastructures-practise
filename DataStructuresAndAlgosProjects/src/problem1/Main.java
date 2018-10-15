package problem1;

import datastructures.StackArray;
import exceptions.DivideByZeroException;
import exceptions.EmptyException;
import exceptions.OverflowException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws OverflowException, EmptyException, DivideByZeroException {
        System.out.println("Hello World!");

        // testing the stack data structure
        StackArray<String> test = new StackArray<>(10);
        test.push("A");
        test.push("B");
        test.push("C");
        System.out.println(test.toString());
        test.pop();
        System.out.println(test.toString());
        System.out.println(test.size());
        System.out.println(test.top());

        // testing the conversions
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("Convert to Postfix");
        InfixToPost infixToPost = new InfixToPost(true, input);
        System.out.println("------ Postfix ------");
        System.out.println(infixToPost.convert());

        // testing the evaluation
        System.out.println("Evaluate Postfix Expression");
        EvalPostfix evalPostfix = new EvalPostfix(true);
        System.out.println("------ Evaluation Result ------");
        System.out.println(evalPostfix.evaluate(infixToPost.convert()));
    }
}
