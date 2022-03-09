package com.mycorp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import java.util.Locale;

public class Solver {

    private static final HashMap<Character,Integer> operationPriority = new HashMap<>();
    static
    {
        operationPriority.put('(',0);
        operationPriority.put('+',1);
        operationPriority.put('-',1);
        operationPriority.put('*',2);
        operationPriority.put('/',2);
        operationPriority.put('^',3);
    }

    private static String getAllNumber(String expression, WrapInt index)
    {
        String number="";
        boolean isEndOfNumberExist=false;
        boolean isCommaExist=false;
        while (!isEndOfNumberExist && index.value<expression.length())
        {
            char symbol = expression.charAt(index.value);
            if (Character.isDigit(symbol))
            {
                number+=symbol;
                index.value++;
            }
            else if (symbol=='.'&& !isCommaExist)
            {
                isCommaExist=true;
                number+=symbol;
                index.value++;
            }
            else
            {
                isEndOfNumberExist=true;
                index.value--;
            }
        }
        return number;
    }

    private static String getAllParameter(String expression, WrapInt index)
    {
        String parameter="";
        boolean isEndOfParameterExist=false;
        while (!isEndOfParameterExist && index.value<expression.length())
        {
            char symbol = expression.charAt(index.value);
            if (Character.isAlphabetic(symbol))
            {
                parameter+=symbol;
                index.value++;
            }
            else
            {
                isEndOfParameterExist=true;
                index.value--;
            }
        }
        return parameter;
    }

    private static String enterValueOfParameter(String nameOfParameter)
    {
        String valueOfParameter="";
        System.out.println("Please, enter value of parameter " + nameOfParameter);
        Scanner console = new Scanner(System.in).useLocale(Locale.US);
        while (!console.hasNextDouble())
        {
            System.out.println("Value of parameter must be a number, try again ( double with comma)");
            console.nextLine();
        }
        valueOfParameter=console.nextLine();
        return valueOfParameter;
    }



    public static double getSolution(String expression)
    {
        String postfixForm = refactorToPostfixForm(expression);
        return getSolutionOfPostfixForm(postfixForm);
    }

    private static String refactorToPostfixForm(String infixForm)
    {
        String postfixExpression = "";
        Stack<Character> stack = new Stack<>();
        ArrayList<String> parameters = new ArrayList<>();

        WrapInt index = new WrapInt();
        while (index.value<infixForm.length())
        {
            char symbol = infixForm.charAt(index.value);

            if (Character.isDigit(symbol))
            {
                postfixExpression+=getAllNumber(infixForm,index)+" ";
            }
            else if (symbol=='(')
            {
                stack.push('(');
            }
            else if (symbol==')')
            {
                while (stack.size()>0&&stack.peek()!='(')
                {
                    postfixExpression+=stack.pop();
                }
                stack.pop();
            }
            else if (operationPriority.containsKey(symbol))
            {
                while (stack.size()>0 && operationPriority.get(stack.peek())>=operationPriority.get(symbol))
                {
                    postfixExpression+=stack.pop();
                }
                stack.push(symbol);
            }
            else if (Character.isAlphabetic(symbol))
            {
                String parameter = getAllParameter(infixForm,index);
                postfixExpression+=parameter+" ";
                if (!parameters.contains(parameter)) parameters.add(parameter);
            }
            index.value++;
        }

        while (!stack.isEmpty())
        {
            postfixExpression+=stack.pop();
        }


        for (String parameter : parameters)
        {
            String valueOfParameter = enterValueOfParameter(parameter);
            postfixExpression = postfixExpression.replace(parameter,valueOfParameter);
        }

        return postfixExpression;
    }

    private static double getSolutionOfPostfixForm(String postfixForm)
    {
        Stack<Double> localValue = new Stack<>();
        WrapInt index = new WrapInt();

        while(index.value<postfixForm.length())
        {
            char symbol = postfixForm.charAt(index.value);

            if (Character.isDigit(symbol))
            {
                String number = getAllNumber(postfixForm, index);
                localValue.push(Double.parseDouble(number));
            }
            else if (operationPriority.containsKey(symbol))
            {
                double second = (localValue.size()>0) ? localValue.pop() : 0;
                double first = (localValue.size()>0) ? localValue.pop() : 0;

                localValue.push(calculate(symbol,first,second));
            }
            index.value++;
        }
        return localValue.pop();
    }

    private static double calculate(char operator, double first, double second)
    {
        double result=0.0;
        switch (operator)
        {
            case '+':
            {
                result=first+second;
               break;
            }
            case '-':
            {
                result=first-second;
                break;
            }
            case '*':
            {
                result=first*second;
                break;
            }
            case '/':
            {
                result=first/second;
                break;
            }
            case '^':
            {
                result=Math.pow(first,second);
                break;
            }
        }
        return result;
    }
}
