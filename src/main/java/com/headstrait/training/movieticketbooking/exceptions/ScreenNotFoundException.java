package com.headstrait.training.movieticketbooking.exceptions;

public class ScreenNotFoundException extends Exception{

    public ScreenNotFoundException(int screenNumber) {
        super(String.format("Screen %s does not exists", screenNumber));
    }

}
