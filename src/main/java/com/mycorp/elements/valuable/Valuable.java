package com.mycorp.elements.valuable;

import com.mycorp.elements.ElementOfExpression;

abstract public class Valuable extends ElementOfExpression {
    protected double value;

    Valuable()
    {
        super("0");
        this.value=0.0;
    }

    Valuable(String name, double value)
    {
        super(name);
        this.value=value;
    }

    public double getValue()
    {
        return value;
    }
}
