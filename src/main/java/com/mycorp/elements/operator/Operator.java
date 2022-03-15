package com.mycorp.elements.operator;

import com.mycorp.elements.ElementOfExpression;

abstract public class Operator extends ElementOfExpression {
    private int priority;

    public Operator()
    {
        super("0");
        this.priority=0;
    }

    public Operator(String name, int priority)
    {
        super(name);
        this.priority=priority;
    }

    public int getPriority()
    {
        return priority;
    }
}
