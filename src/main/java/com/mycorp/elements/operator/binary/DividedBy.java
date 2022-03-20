package com.mycorp.elements.operator.binary;

import com.mycorp.exceptions.DivisionByZeroInExpressionException;

/**
 * class divided by
 */
public class DividedBy extends BinaryOperation{

    /**
     * Making a "divided by" element
     */
    public DividedBy()
    {
        super("/",2);
    }

    /**
     * Dividing one value by another
     * @param firstValue first value for operation
     * @param secondValue second value for operation
     * @return result of dividing one value by another
     */
    @Override
    public double calculate(double firstValue, double secondValue) {
        if (secondValue == 0 ) throw new DivisionByZeroInExpressionException("There is a division by zero in the expression");
        return firstValue/secondValue;
    }
}
