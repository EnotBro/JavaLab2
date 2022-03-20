package com.mycorp.elements.operator.binary;

/**
 * class multipliedBy
 */
public class MultipliedBy extends BinaryOperation {

    /**
     * Making a "multiplied by" element
     */
    public MultipliedBy()
    {
        super("*",2);
    }

    /**
     * Product of the two values
     * @param firstValue first value for operation
     * @param secondValue second value for operation
     * @return Product of the two values
     */
    @Override
    public double calculate(double firstValue, double secondValue) {
        return firstValue*secondValue;
    }
}
