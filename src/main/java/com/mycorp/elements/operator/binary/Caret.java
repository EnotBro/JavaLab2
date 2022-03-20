package com.mycorp.elements.operator.binary;

/**
 * class caret
 */
public class Caret extends BinaryOperation{

    /**
     * Making a caret
     */
    public Caret()
    {
        super("^",3);
    }

    /**
     * Raising a value to the power of another
     * @param firstValue first value for operation
     * @param secondValue second value for operation
     * @return result of raising
     */
    @Override
    public double calculate(double firstValue, double secondValue) {
        return Math.pow(firstValue,secondValue);
    }
}
