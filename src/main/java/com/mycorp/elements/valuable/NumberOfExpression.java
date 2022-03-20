package com.mycorp.elements.valuable;

/**
 * class - number
 */
public class NumberOfExpression extends Valuable {

    /**
     * default constructor. Making a number
     */
    public NumberOfExpression()
    {
        super("0",0.0);
    }

    /**
     * Making a number with value (name - string version of value)
     * @param value
     */
    public NumberOfExpression(double value)
    {
        super(String.valueOf(value),value);
    }

}
