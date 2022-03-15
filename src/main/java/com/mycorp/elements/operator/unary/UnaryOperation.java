package com.mycorp.elements.operator.unary;

import com.mycorp.elements.operator.Operator;

abstract public class UnaryOperation extends Operator {
    UnaryOperation()
    {
        super("0",4);
    }

    UnaryOperation(String name)
    {
        super(name,4);
    }

    abstract public double calculate(double value);
}
