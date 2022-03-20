package com.mycorp.elements.operator.binary;

/**
 * class plus
 */
public class Plus extends BinaryOperation {

    /**
     * Making a plus
     */
    public Plus()
    {
        super("+",1);
    }

    /**
     * Sum of the two values
     * @param firstValue first value for operation
     * @param secondValue second value for operation
     * @return Sum of the two values
     */
    @Override
    public double calculate(double firstValue, double secondValue) {
        return firstValue+secondValue;
    }
}
