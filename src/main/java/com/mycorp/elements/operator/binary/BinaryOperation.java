package com.mycorp.elements.operator.binary;

import com.mycorp.elements.operator.Operator;

/**
 * class describing binary operation of expression
 */
abstract public class BinaryOperation extends Operator {

    /**
     * default constructor. Making a binary operation of expression
     */
    BinaryOperation()
    {
        super("0",0);
    }

    /**
     * Making a binary operation of expression with name and priority
     * @param name string version of operation
     * @param priority priority of operation
     */
    BinaryOperation(String name, int priority)
    {
        super(name,priority);
    }

    /**
     * Calculating of the operation
     * @param firstValue first value for operation
     * @param secondValue second value for operation
     * @return result of operation
     */
    abstract public double calculate(double firstValue, double secondValue);
}
