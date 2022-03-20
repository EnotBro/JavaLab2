package com.mycorp.elements.operator;

import com.mycorp.elements.ElementOfExpression;

/**
 * class describing elements of expression that can do math operations
 */
abstract public class Operator extends ElementOfExpression {

    /**
     * priority of operation
     */
    private int priority;

    /**
     * default constructor. Making an operator element of expression
     */
    public Operator()
    {
        super("0");
        this.priority=0;
    }

    /**
     * Making an operator element of expression with name and priority
     * @param name string version of operator
     * @param priority priority of operator
     */
    public Operator(String name, int priority)
    {
        super(name);
        this.priority=priority;
    }

    /**
     * Gives priority of the operator
     * @return priority of the operator
     */
    public int getPriority()
    {
        return priority;
    }
}
