package com.mycorp.elements;

abstract public class ElementOfExpression {
    private String name;
    public ElementOfExpression()
    {
        this.name=" ";
    }

    public ElementOfExpression(String name)
    {
        this.name=name;
    }

    public String getName()
    {
        return name;
    }
}
