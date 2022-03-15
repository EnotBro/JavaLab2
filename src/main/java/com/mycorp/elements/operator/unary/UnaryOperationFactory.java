package com.mycorp.elements.operator.unary;

public class UnaryOperationFactory {
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
