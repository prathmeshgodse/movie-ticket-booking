package com.headstrait.training.movieticketbooking.exceptions;

public class ScreenAlreadyExistsException extends Exception{

    public ScreenAlreadyExistsException(int screenNumber) {
        super(String.format("Screen %s already exists", screenNumber));
    }
}