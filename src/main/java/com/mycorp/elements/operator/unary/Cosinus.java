package com.mycorp.elements.operator.unary;

/**
 * class cosinus
 */
public class Cosinus extends UnaryOperation {

    /**
     * Making a cosinus
     */
    public Cosinus()
    {
        super("cos");
    }

    /**
     * Cosinus of value
     * @param value value for operation
     * @return Cosinus of value
     */
    @Override
    public double calculate(double value) {
        return Math.cos(value);
    }
}
