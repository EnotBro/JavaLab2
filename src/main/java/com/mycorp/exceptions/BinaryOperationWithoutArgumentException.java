package com.mycorp.exceptions;

/**
 * class exception binary operation without argument
 */
public class BinaryOperationWithoutArgumentException extends ExpressionException{

    public BinaryOperationWithoutArgumentException(String message){
        super(message);
    }
}
