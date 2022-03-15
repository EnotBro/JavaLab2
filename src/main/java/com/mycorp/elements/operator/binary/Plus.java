package com.mycorp.elements.operator.binary;

public class Plus extends BinaryOperation {
    Plus()
    {
        super("+",1);
    }

    @Override
    public double calculate(double firstValue, double secondValue) {
        return firstValue+secondValue;
    }
}
