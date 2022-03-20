package com.mycorp.elements.valuable;

import com.mycorp.elements.ElementOfExpression;

/**
 * class describing all elements of math expression that can have a value
 */
abstract public class Valuable extends ElementOfExpression {

    /**
     * value of element
     */
    protected double value;

    /**
     * default constructor. Making a valuable element of expression
     */
    Valuable()
    {
        super("0");
        this.value=0.0;
    }

    /**
     * Making a valuable element of expression with name and value
     * @param name string version of element
     * @param value value of element of expression
     */
    Valuable(String name, double value)
    {
        super(name);
        this.value=value;
    }

    /**
     * Gives value of valuable element
     * @return value of valuable element
     */
    public double getValue()
    {
        return value;
    }
}
