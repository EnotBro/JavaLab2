package com.mycorp.elements.operator.binary;

/**
 * class-creator of binary operation
 */
public class BinaryOperationFactory {

    /**
     * Making a binary operation by its type
     * @param type type of binary operation
     * @return new binary operation
     */
    static public BinaryOperation createBinaryOperation(String type)
    {
        BinaryOperation operation = null;

        switch(type)
        {
            case "+":
            {
                operation = new Plus();
                break;
            }

            case "-":
            {
                operation = new Minus();
                break;
            }

            case "*":
            {
                operation = new MultipliedBy();
                break;
            }

            case "/":
            {
                operation = new DividedBy();
                break;
            }

            case "^":
            {
                operation = new Caret();
                break;
            }
        }
        return operation;
    }
}
