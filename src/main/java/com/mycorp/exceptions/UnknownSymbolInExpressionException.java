package com.mycorp.exceptions;

/**
 * class exception unknown symbol in expression
 */
public class UnknownSymbolInExpressionException extends  ExpressionException{

    public UnknownSymbolInExpressionException(String message){
        super(message);
    }
}
