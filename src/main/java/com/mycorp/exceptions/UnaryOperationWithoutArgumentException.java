package com.mycorp.exceptions;

/**
 * class exception unary operation without argument
 */
public class UnaryOperationWithoutArgumentException extends  ExpressionException{

    public UnaryOperationWithoutArgumentException(String message){
        super(message);
    }
}
