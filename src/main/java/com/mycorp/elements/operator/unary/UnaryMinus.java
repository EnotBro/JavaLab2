package com.mycorp.elements.operator.unary;

/**
 * class unary minus
 */
public class UnaryMinus extends UnaryOperation {

    /**
     *  Making a unary minus
     */
    public UnaryMinus()
    {
        super("-");
    }

    /**
     * changing  sign of  the value
     * @param value value for operation
     * @return value with another sign
     */
    @Override
    public double calculate(double value) {
        return -value;
    }
}
