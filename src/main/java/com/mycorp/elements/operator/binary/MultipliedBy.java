package com.mycorp.elements.operator.binary;

public class MultipliedBy extends BinaryOperation {
    MultipliedBy()
    {
        super("*",2);
    }

    @Override
    public double calculate(double firstValue, double secondValue) {
        return firstValue*secondValue;
    }
}
