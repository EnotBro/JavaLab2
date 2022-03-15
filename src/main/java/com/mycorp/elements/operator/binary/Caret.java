package com.mycorp.elements.operator.binary;

public class Caret extends BinaryOperation{
    Caret()
    {
        super("^",3);
    }

    @Override
    public double calculate(double firstValue, double secondValue) {
        return Math.pow(firstValue,secondValue);
    }
}
