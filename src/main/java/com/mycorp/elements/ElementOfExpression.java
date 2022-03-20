package com.mycorp.elements;

/**
 * class describing all elements of math expression
 */
abstract public class ElementOfExpression {

    /**
     * String version of element
     */
    private String name;

    /**
     * default constructor. Making an element of expression
     */
    public ElementOfExpression()
    {
        this.name=" ";
    }

    /**
     * Constructor. Making an element of expression with string version of element
     * @param name string version of element
     */
    public ElementOfExpression(String name)
    {
        this.name=name;
    }

    /**
     * Gives string version of element
     * @return string version of element
     */
    public String getName()
    {
        return name;
    }
}
