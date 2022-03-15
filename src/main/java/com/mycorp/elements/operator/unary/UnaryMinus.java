package com.mycorp.elements.operator.unary;

public class UnaryMinus extends UnaryOperation {
    UnaryMinus()
    {
        super("-");
    }

    @Override
    public double calculate(double value) {
        return -value;
    }
}
