package com.mycorp;


import com.mycorp.elements.ElementOfExpression;
import com.mycorp.elements.operator.Bracket;
import com.mycorp.elements.operator.Operator;
import com.mycorp.elements.operator.binary.BinaryOperationFactory;
import com.mycorp.elements.operator.binary.BinaryOperation;
import com.mycorp.elements.operator.unary.UnaryOperation;
import com.mycorp.elements.operator.unary.UnaryOperationFactory;
import com.mycorp.elements.valuable.Parameter;
import com.mycorp.elements.valuable.Number;
import com.mycorp.elements.valuable.Valuable;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Locale;
import java.util.List;

public class Solver {

    private static final List<String> availableOperators = new ArrayList<>();
    static
    {
        availableOperators.add("(");
        availableOperators.add("+");
        availableOperators.add("-");
        availableOperators.add("*");
        availableOperators.add("/");
        availableOperators.add("^");
        availableOperators.add("sin");
        availableOperators.add("cos");
    }


    private static Double getAllNumber(String expression, WrapInt index)
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
        return Double.parseDouble(number);
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
        return parameter.toLowerCase(Locale.ROOT);
    }

    private static Double enterValueOfParameter(String nameOfParameter)
    {
        String valueOfParameter="";
        System.out.println("Please, enter value of parameter " + nameOfParameter);
        Scanner console = new Scanner(System.in).useLocale(Locale.US);
        while (!console.hasNextDouble())
        {
            System.out.println("Value of parameter must be a number, try again ( double with dot)");
            console.nextLine();
        }
        valueOfParameter=console.nextLine();
        return Double.parseDouble(valueOfParameter);
    }



    public static double getSolution(String expression)
    {
        ArrayList<ElementOfExpression> postfixForm = refactorToPostfixForm(expression);
        return getSolutionOfPostfixForm(postfixForm);
    }

    private static Parameter getParameterFromListByName(ArrayList<Parameter> parameters, String name)
    {
        Parameter result=null;
        int index=0;
        while(index<parameters.size()&& result==null)
        {
            Parameter tmp = parameters.get(index);
            if(tmp.getName()==name)result=tmp;
        }
        return result;
    }

    private static void putNewOperatorToStack(ArrayList<ElementOfExpression> postfixExpression, Stack<Operator> stack, Operator newOperator)
    {
        while (stack.size()>0 && stack.peek().getPriority()>=newOperator.getPriority())
        {
            postfixExpression.add(stack.pop());
        }
        stack.push(newOperator);
    }

    private static ArrayList<ElementOfExpression> refactorToPostfixForm(String infixForm)
    {
        final ArrayList<ElementOfExpression> postfixExpression = new ArrayList<>();

        Stack<Operator> stack = new Stack<>();
        ArrayList<Parameter> parameters = new ArrayList<>();

        WrapInt index = new WrapInt();
        while (index.value<infixForm.length())
        {
            char symbol = infixForm.charAt(index.value);

            if (Character.isDigit(symbol))
            {
                postfixExpression.add(new Number(getAllNumber(infixForm,index)));
            }
            else if (symbol=='(')
            {
                stack.push(new Bracket());
            }
            else if (symbol==')')
            {
                while (stack.size()>0&&stack.peek().getClass()!=Bracket.class)
                {
                    postfixExpression.add(stack.pop());
                }
                stack.pop();
            }
            else if (availableOperators.contains(String.valueOf(symbol)))
            {
                Operator operator = null;
                if (symbol=='-'&& (index.value == 0 || (index.value > 1 &&
                        availableOperators.contains( String.valueOf(infixForm.charAt(index.value-1))))))
                    operator= UnaryOperationFactory.createUnaryOperation("-");
                else
                {
                    operator= BinaryOperationFactory.createBinaryOperation(String.valueOf(symbol));
                }

                putNewOperatorToStack(postfixExpression,stack,operator);
            }
            else if (Character.isAlphabetic(symbol))
            {
                String element = getAllParameter(infixForm,index);
                if (availableOperators.contains(element))
                {
                    Operator operator = UnaryOperationFactory.createUnaryOperation(element);
                    putNewOperatorToStack(postfixExpression,stack,operator);
                }
                else
                {
                    Parameter parameter = getParameterFromListByName(parameters,element);
                    if(parameter==null)
                    {
                        Parameter newParameter = new Parameter(element);
                        postfixExpression.add(newParameter);
                        parameters.add(newParameter);
                    }
                    else postfixExpression.add(parameter);
                }
            }
            index.value++;
        }

        while (!stack.isEmpty())
        {
            postfixExpression.add(stack.pop());
        }

        for (Parameter parameter : parameters)
        {
            double valueOfParameter = enterValueOfParameter(parameter.getName());
            parameter.setValue(valueOfParameter);
        }

        return  postfixExpression;
    }

    private static double getSolutionOfPostfixForm(ArrayList<ElementOfExpression> postfixForm)
    {
        Stack<Double> localValue = new Stack<>();
        WrapInt index = new WrapInt();

        while(index.value<postfixForm.size())
        {
            ElementOfExpression element = postfixForm.get(index.value);

            if (element.getClass()==Number.class||element.getClass()==Parameter.class)
            {
                localValue.push(((Valuable) element).getValue());
            }
            else
            {
                if (element instanceof UnaryOperation)
                {
                    double last = (localValue.size()>0) ? localValue.pop() : 0;
                    localValue.push(((UnaryOperation) element).calculate(last));
                }
                else {
                    double second = (localValue.size() > 0) ? localValue.pop() : 0;
                    double first = (localValue.size() > 0) ? localValue.pop() : 0;
                    localValue.push(((BinaryOperation) element).calculate(first,second));
                }
            }
            index.value++;
        }
        return localValue.pop();
    }
}

