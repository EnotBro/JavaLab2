package com.mycorp.elements.operator.unary;

/**
 * class-creator of unary operation
 */
public class UnaryOperationFactory {

    /**
     * Making a unary operation by its type
     * @param type type of unary operation
     * @return new unary operation
     */
    static public UnaryOperation createUnaryOperation(String type)
    {
        UnaryOperation operation = null;

        switch(type)
        {
            case "-":
            {
                operation = new UnaryMinus();
                break;
            }

            case "sin":
            {
                operation = new Sinus();
                break;
            }

            case "cos":
            {
                operation = new Cosinus();
                break;
            }
        }
        return operation;
    }
}
