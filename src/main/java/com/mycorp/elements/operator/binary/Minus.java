package com.mycorp.elements.operator.binary;

public class Minus extends BinaryOperation{
    Minus()
    {
        super("-",1);
    }

    @Override
    public double calculate(double firstValue, double secondValue) {
        return firstValue-secondValue;
    }
}
