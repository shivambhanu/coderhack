package com.backend.coderhack.exceptions;

public class InvalidScoreException extends RuntimeException{
    public InvalidScoreException(){
        super();
    }

    public InvalidScoreException(String message){
        super(message);
    }
}
