package com.mycorp.exceptions;

/**
 * class describing all exception that can be in math expression
 */
public class ExpressionException extends RuntimeException{

    public ExpressionException(String message){
        super(message);
    }
}
