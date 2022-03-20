package com.mycorp.elements.valuable;

/**
 * class parameter
 */
public class Parameter extends Valuable {

    /**
     * default constructor. Making a parameter
     */
    public Parameter()
    {
        super("0",0.0);
    }

    /**
     * Making a parameter with name
     * @param name
     */
    public Parameter(String name)
    {
        super(name,0.0);
    }

    /**
     * assigning a value for the parameter
     * @param value value that you want to assign for parameter
     */
    public void setValue(double value)
    {
        this.value=value;
    }
}
