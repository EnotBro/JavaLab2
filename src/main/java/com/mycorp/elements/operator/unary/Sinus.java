package com.mycorp.elements.operator.unary;

/**
 * class sinus
 */
public class Sinus extends UnaryOperation {

    /**
     *  Making a sinus
     */
    public Sinus()
    {
        super("sin");
    }

    /**
     * Sinus of value
     * @param value value for operation
     * @return Sinus of value
     */
    @Override
    public double calculate(double value) {
        return Math.sin(value);
    }
}
