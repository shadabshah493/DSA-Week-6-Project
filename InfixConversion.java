package practice;

import java.util.Scanner;
import java.util.Stack;

public class InfixConversion {

    private static int precedencePostfix(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    public static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == ' ') continue;

            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedencePostfix(c) <= precedencePostfix(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }


    private static int precedencePrefix(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    private static String infixToPostfixForPrefix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == ' ') continue;

            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                if (!stack.isEmpty()) stack.pop();
            } else {
                while (!stack.isEmpty() && precedencePrefix(c) <= precedencePrefix(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    private static String reverseAndSwap(String expr) {
        StringBuilder reversed = new StringBuilder();
        for (int i = expr.length() - 1; i >= 0; i--) {
            char c = expr.charAt(i);
            if (c == '(')
                reversed.append(')');
            else if (c == ')')
                reversed.append('(');
            else
                reversed.append(c);
        }
        return reversed.toString();
    }

    public static String infixToPrefix(String infix) {
        String reversed = reverseAndSwap(infix);
        String postfix = infixToPostfixForPrefix(reversed);
        String prefix = new StringBuilder(postfix).reverse().toString();
        return prefix;
    }




    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter an infix expression (e.g., A + B * C): ");
        String infix = scanner.nextLine();

        String postfix = infixToPostfix(infix);
        String prefix = infixToPrefix(infix);

        System.out.println("Postfix Expression: " + postfix);
        System.out.println("Prefix Expression: " + prefix);

        scanner.close();
    }
}