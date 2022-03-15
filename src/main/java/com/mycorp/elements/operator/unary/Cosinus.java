package com.mycorp.elements.operator.unary;

public class Cosinus extends UnaryOperation {

    Cosinus()
    {
        super("cos");
    }

    @Override
    public double calculate(double value) {
        return Math.cos(value);
    }
}
