package com.mycorp.exceptions;

/**
 * class exception closing brackets more than opening
 */
public class ClosingBracketsMoreThanOpeningException extends ExpressionException{

    public ClosingBracketsMoreThanOpeningException(String message){
        super(message);
    }
}
