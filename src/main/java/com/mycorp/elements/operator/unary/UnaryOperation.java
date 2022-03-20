package com.mycorp.elements.operator.unary;

import com.mycorp.elements.operator.Operator;

/**
 * class describing unary operation of expression
 */
abstract public class UnaryOperation extends Operator {

    /**
     * default constructor. Making a unary operation of expression
     */
    UnaryOperation()
    {
        super("0",4);
    }

    /**
     * Making a unary operation of expression with string version of operation
     * @param name string version of operation
     */
    UnaryOperation(String name)
    {
        super(name,4);
    }

    /**
     * Calculating of the operation
     * @param value value for operation
     * @return result of operation
     */
    abstract public double calculate(double value);
}
