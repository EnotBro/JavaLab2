package com.mycorp.exceptions;

/**
 * class exception division by zero in expression
 */
public class DivisionByZeroInExpressionException extends ExpressionException {
    public DivisionByZeroInExpressionException(String message){
        super(message);
    }
}
