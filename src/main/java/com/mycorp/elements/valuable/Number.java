package com.mycorp.elements.valuable;

public class Number extends Valuable {

    public Number()
    {
        super("0",0.0);
    }

    public Number(double value)
    {
        super(String.valueOf(value),value);
    }

}
