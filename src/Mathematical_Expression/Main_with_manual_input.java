package Mathematical_Expression;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;


/**
 * ------------------------------------------------------------------------------------------------*
 * File name: Main_with_manual_input
 * Project name: Compiler Design
 * Author: SAJJAD AHMED NILOY
 * Created on: 11:15 PM
 * License:
 * ------------------------------------------------------------------------------------------------*
 **/


public class Main_with_manual_input
{
    private static HashMap<String, Integer> onStackPrecedence = new HashMap<>();

    public static void main(String[] args)
    {
        onStackPrecedence.put("+", 1);
        onStackPrecedence.put("-", 1);
        onStackPrecedence.put("*", 2);
        onStackPrecedence.put("/", 2);
        onStackPrecedence.put("^", 9);
        onStackPrecedence.put("(", 0);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter no of identifiers (n):");
        int n = scanner.nextInt();
        HashMap<String, Integer> identifiers = new HashMap<>();
        System.out.println("Enter identifiers with value: (ex: a = 1)");
        String regEx = "\\w*\\s=\\s\\d{1,}";      //to ensure "identifier(space)=(space)int_Value"
        for (int i = 0; i < n; )
        {
            String s = scanner.nextLine();
            s = s.trim();
            if (!s.equals("") && s.matches(regEx))
            {
                StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
                String id = stringTokenizer.nextToken();
                stringTokenizer.nextToken();
                int val = Integer.parseInt(stringTokenizer.nextToken());
                identifiers.put(id, val);
                i++;
            } else
            {
                System.out.println("invalid expression. please check and input again:");
            }
        }
        System.out.println("Enter no of expressions (m):");
        int m = scanner.nextInt();
        String[] expressions = new String[m];
        System.out.println("Enter expressions: (ex: a * b + a * c + b * c)");
        for (int i = 0; i < m; )
        {
            String s = scanner.nextLine();
            if (!s.equals(""))
            {
                expressions[i] = s;
                i++;
            }
        }

        System.out.println("Output:");
        for (String eachExpression : expressions)
        {
            String postfix = toPostfix(eachExpression, identifiers);
//            System.out.println("Postfix: " + postfix);
            if (postfix.equals("Compilation Error"))
                System.out.println("Compilation Error");
            else
                System.out.println(evaluate(postfix, identifiers));

        }
    }

    private static String toPostfix(String eachExpression, HashMap<String, Integer> valueMap)
    {
        StringBuilder output = new StringBuilder();
        Stack<String> stack = new Stack<>();

        StringTokenizer tokenizer = new StringTokenizer(eachExpression, " ");
        while (tokenizer.hasMoreTokens())
        {
            String element = tokenizer.nextToken().trim();
            if (valueMap.keySet().contains(element))   //If it is operand, output it
            {
                output.append(" ").append(element);
            } else if (element.equals("("))     // If it is opening parenthesis, push it on stack
            {
                stack.push(element);
            } else if (isOperator(element))    //If it is an operator, then
            {
                while (true)
                {
                    if (stack.isEmpty())    //If stack is empty, push operator on stack.
                    {
                        stack.push(element);
                        break;
                    } else if (stack.peek().equals("("))   //) If the top of stack is opening parenthesis, push operator on stack
                    {
                        stack.push(element);
                        break;
                    } else
                    {
                        if (onStackPrecedence.get(element) > onStackPrecedence.get(stack.peek()))
                        {
                            stack.push(element);
                            break;
                        } else
                        {
                            output.append(" ").append(stack.pop());
                        }
                    }
                }
            } else if (element.equals(")"))
            {
                while (true)
                {
                    String sta = stack.peek();
                    if (sta.equals("("))
                    {
                        stack.pop();
                        break;
                    } else
                    {
                        output.append(" ").append(stack.pop());
                    }
                }
            } else
            {
                return "Compilation Error";
            }
//            System.out.println("log-postfix: " + output); // uncomment to debug postfix process
        }


        while (stack.size() != 0)
        {
            output.append(" ").append(stack.pop());
        }


        return output.toString();
    }

    private static String evaluate(String eachExpression, HashMap<String, Integer> valueMap)
    {
        Stack<String> stack = new Stack<>();
        StringTokenizer tokenizer = new StringTokenizer(eachExpression, " ");
        while (tokenizer.hasMoreTokens())
        {
            String element = tokenizer.nextToken().trim();
            if (valueMap.keySet().contains(element)) //If it is operand, output it
            {
                stack.push("" + valueMap.get(element));
            } else if (isOperator(element))
            {
                int right = Integer.parseInt(stack.pop());  //  pop the stack for the right hand operand
                int left = Integer.parseInt(stack.pop());  //  pop the stack for the left hand operand
                stack.push(performOperation(left, right, element));  //apply the operator to the two operands  push the result onto the stack
            }
        }
        if (stack.size() == 1)
        {
            return stack.pop();
        } else
            return "Error";
    }

    private static String performOperation(int left, int right, String operator)
    {
        String out = "";
        switch (operator.trim())
        {
            case "+":
                out = (left + right) + "";
                return out;
            case "-":
                out = (left - right) + "";
                return out;
            case "*":
                out = (left * right) + "";
                return out;
            case "/":
                out = (left / right) + "";
                return out;
            case "^":
                out = (Math.pow(left, right)) + "";
                return out;
        }
        return out;
    }

    private static boolean isOperator(String s)
    {
        if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^"))
            return true;
        else return false;
    }
}