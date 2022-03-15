package com.mycorp.elements.operator.unary;

public class Sinus extends UnaryOperation {
    Sinus()
    {
        super("sin");
    }

    @Override
    public double calculate(double value) {
        return Math.sin(value);
    }
}
