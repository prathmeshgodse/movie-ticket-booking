package com.headstrait.training.movieticketbooking.exceptions;


public class MovieAlreadyExistsException extends Exception{

    public MovieAlreadyExistsException(String movieName) {
        super(String.format("A Movie with name %s already Exists", movieName));
    }
}
