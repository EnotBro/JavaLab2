package com.mycorp.elements.operator.binary;

import com.mycorp.elements.operator.Operator;

abstract public class BinaryOperation extends Operator {

    BinaryOperation()
    {
        super("0",0);
    }

    BinaryOperation(String name, int priority)
    {
        super(name,priority);
    }

    abstract public double calculate(double firstValue, double secondValue);
}
