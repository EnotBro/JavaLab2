package com.mycorp.elements.valuable;

public class Parameter extends Valuable {

    public Parameter()
    {
        super("0",0.0);
    }

    public Parameter(String name)
    {
        super(name,0.0);
    }

    public void setValue(double value)
    {
        this.value=value;
    }
}
