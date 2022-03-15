package com.mycorp.elements.operator.binary;

public class BinaryOperationFactory {
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
