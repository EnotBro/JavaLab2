package com.mycorp;

import com.mycorp.elements.*;
import com.mycorp.elements.valuable.*;
import com.mycorp.elements.operator.*;
import com.mycorp.elements.operator.unary.*;
import com.mycorp.elements.operator.binary.*;
import com.mycorp.exceptions.*;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Locale;
import java.util.List;

/**
 * Class "solver" that can solve math expression with numbers, parameters, unary and binary operations
 */
public class Solver {

    /**
     * List of available operation in expression
     */
    private static final List<String> availableOperators = new ArrayList<>();
    static
    {
        availableOperators.add(new Bracket().getName());
        availableOperators.add(new Plus().getName());
        availableOperators.add(new Minus().getName());
        availableOperators.add(new MultipliedBy().getName());
        availableOperators.add(new DividedBy().getName());
        availableOperators.add(new Caret().getName());
        availableOperators.add(new Sinus().getName());
        availableOperators.add(new Cosinus().getName());
    }

    /**
     * Postfix form of math expression
     */
    private static ArrayList<ElementOfExpression> postfixExpression = new ArrayList<>();

    /**
     * Gives solution of expression
     * @param expression string of math expression
     * @return value of the expression
     * @throws ExpressionException expression contains errors, method can't return right result
     */
    public static double getSolution(String expression) throws ExpressionException
    {
        postfixExpression = new ArrayList<>();
        ConvertToPostfixForm(expression);
        return getSolutionOfPostfixForm();
    }

    /**
     * Convert expression from infix form to postfix form
     * @param infixForm string of usual math expression
     * @throws ExpressionException expression contains errors, method can't return right result
     */
    private static void ConvertToPostfixForm(String infixForm) throws ExpressionException
    {
        String infixExpression = infixForm.trim();
        infixExpression=infixExpression.replaceAll(" ","");


        Stack<Operator> stack = new Stack<>();
        ArrayList<Parameter> parameters = new ArrayList<>();

        WrapInt index = new WrapInt();
        while (index.value<infixExpression.length())
        {
            char symbol = infixExpression.charAt(index.value);

            if (Character.isDigit(symbol))
            {
                postfixExpression.add(new NumberOfExpression(getAllNumber(infixExpression,index)));
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

                if (stack.size()==0){
                    throw new ClosingBracketsMoreThanOpeningException("There are more closing brackets than opening ones");
                }
                else stack.pop();
            }
            else if (availableOperators.contains(String.valueOf(symbol)))
            {
                Operator operator;
                if (symbol=='-'&& (index.value == 0 || (index.value > 1 &&
                        availableOperators.contains( String.valueOf(infixExpression.charAt(index.value-1))))))
                    operator= UnaryOperationFactory.createUnaryOperation("-");
                else
                {
                    operator= BinaryOperationFactory.createBinaryOperation(String.valueOf(symbol));
                }

                putNewOperatorToStack(stack,operator);
            }
            else if (Character.isAlphabetic(symbol))
            {
                String element = getAllName(infixExpression,index);
                if (availableOperators.contains(element))
                {
                    Operator operator = UnaryOperationFactory.createUnaryOperation(element);
                    putNewOperatorToStack(stack,operator);
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
            else
            {
                throw new UnknownSymbolInExpressionException("There is an unknown symbol in the expression");
            }
            index.value++;
        }

        while (!stack.isEmpty())
        {
            Operator operator = stack.pop();
            if (operator.getClass() == Bracket.class)
            {
                throw new OpeningBracketsMoreThanClosingException("There are more opening brackets than closing ones");
            }
            else postfixExpression.add(operator);
        }

        for (Parameter parameter : parameters)
        {
            double valueOfParameter = enterValueOfParameter(parameter.getName());
            parameter.setValue(valueOfParameter);
        }
    }

    /**
     * Gives all number from string
     * @param expression math expression
     * @param index a place of reading the string-expression
     * @return double value of all number
     */
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

    /**
     * Gives all number from string (parameter or operation)
     * @param expression math expression
     * @param index a place of reading the string-expression
     * @return string name of all element
     */
    private static String getAllName(String expression, WrapInt index)
    {
        String name ="";
        boolean isEndOfNameExist =false;
        while (!isEndOfNameExist && index.value<expression.length())
        {
            char symbol = expression.charAt(index.value);
            if (Character.isAlphabetic(symbol))
            {
                name +=symbol;
                index.value++;
            }
            else
            {
                isEndOfNameExist =true;
                index.value--;
            }
        }
        return name.toLowerCase(Locale.ROOT);
    }

    /**
     * Entering value of the parameter by its name
     * @param nameOfParameter name of the parameter
     * @return value of the parameter
     */
    private static Double enterValueOfParameter(String nameOfParameter)
    {
        String valueOfParameter;
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

    /**
     * Gives parameter from list by its name
     * @param parameters list of parameters
     * @param name name of the parameter
     * @return the parameter with the name or null if it is not exist
     */
    private static Parameter getParameterFromListByName(ArrayList<Parameter> parameters, String name)
    {
        Parameter result=null;
        int index=0;
        while(index<parameters.size()&& result==null)
        {
            Parameter tmp = parameters.get(index);
            if(tmp.getName().equals(name))result=tmp;
            index++;
        }
        return result;
    }

    /**
     * Puts new operator in stack by priority
     * @param stack stack of operators
     * @param newOperator operator that we want to put into stack
     */
    private static void putNewOperatorToStack( Stack<Operator> stack, Operator newOperator)
    {
        while (stack.size()>0 && stack.peek().getPriority()>=newOperator.getPriority())
        {
            postfixExpression.add(stack.pop());
        }
        stack.push(newOperator);
    }


    /**
     * Gives solution of postfix form of expression. It works with list of elements of expression
     * @return solution of expression
     * @throws ExpressionException expression contains errors, method can't return right result
     */
    private static double getSolutionOfPostfixForm() throws ExpressionException
    {
        Stack<Double> localValue = new Stack<>();
        WrapInt index = new WrapInt();

        while(index.value<postfixExpression.size())
        {
            ElementOfExpression element = postfixExpression.get(index.value);

            if (element.getClass()==NumberOfExpression.class||element.getClass()==Parameter.class)
            {
                localValue.push(((Valuable) element).getValue());
            }
            else
            {
                if (element instanceof UnaryOperation)
                {
                    double last ;
                    if (localValue.size()>0) last = localValue.pop();
                    else throw new UnaryOperationWithoutArgumentException("There is an unary operation without argument");
                    localValue.push(((UnaryOperation) element).calculate(last));
                }
                else {
                    double first, second;
                    if (localValue.size()>0) second = localValue.pop();
                    else throw new BinaryOperationWithoutArgumentException("There is a binary operation without argument");

                    if (localValue.size()>0) first = localValue.pop();
                    else throw new BinaryOperationWithoutArgumentException("There is a binary operation without argument");

                    localValue.push(((BinaryOperation) element).calculate(first,second));
                }
            }
            index.value++;
        }
        return localValue.pop();
    }
}

