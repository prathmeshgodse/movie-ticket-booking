package com.headstrait.training.movieticketbooking.exceptions;

public class MovieNotFoundException extends Exception{
    public MovieNotFoundException(String movieName) {
        super(String.format("Movie with name %s not found", movieName));
    }
}
