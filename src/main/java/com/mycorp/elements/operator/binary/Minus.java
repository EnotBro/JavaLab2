package com.mycorp.elements.operator.binary;

/**
 * class minus
 */
public class Minus extends BinaryOperation{

    /**
     * Making a minus
     */
    public Minus()
    {
        super("-",1);
    }

    /**
     * Difference between the two values
     * @param firstValue first value for operation
     * @param secondValue second value for operation
     * @return Difference between the two values
     */
    @Override
    public double calculate(double firstValue, double secondValue) {
        return firstValue-secondValue;
    }
}
