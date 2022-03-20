package com.mycorp.exceptions;

/**
 * class exception opening brackets more than closing
 */
public class OpeningBracketsMoreThanClosingException extends ExpressionException{

    public OpeningBracketsMoreThanClosingException(String message){
        super(message);
    }
}
