package com.mycorp.elements.operator.binary;

public class DividedBy extends BinaryOperation{
    DividedBy()
    {
        super("/",2);
    }

    @Override
    public double calculate(double firstValue, double secondValue) {
        return firstValue/secondValue;
    }
}
