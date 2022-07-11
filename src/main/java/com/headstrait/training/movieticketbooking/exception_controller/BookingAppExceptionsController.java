package com.headstrait.training.movieticketbooking.exception_controller;

import com.headstrait.training.movieticketbooking.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BookingAppExceptionsController {

    @ResponseBody
    @ExceptionHandler (ScreenAlreadyExistsException.class)
    @ResponseStatus (HttpStatus.CONFLICT)
    String screenAlreadyExists (ScreenAlreadyExistsException e) { return e.getMessage();}

    @ResponseBody
    @ExceptionHandler (ScreenNotFoundException.class)
    @ResponseStatus (HttpStatus.NOT_FOUND)
    String screenNotFound (ScreenNotFoundException e) { return e.getMessage();}

    @ResponseBody
    @ExceptionHandler (InvalidSeatCountException.class)
    @ResponseStatus (HttpStatus.BAD_REQUEST)
    String invalidSeatCount (InvalidSeatCountException e) { return e.getMessage();}


    @ResponseBody
    @ExceptionHandler (MovieAlreadyExistsException.class)
    @ResponseStatus (HttpStatus.CONFLICT)
    String movieAlreadyExists (MovieAlreadyExistsException e) { return e.getMessage();}

    @ResponseBody
    @ExceptionHandler (MovieNotFoundException.class)
    @ResponseStatus (HttpStatus.NOT_FOUND)
    String screenNotFound (MovieNotFoundException e) { return e.getMessage();}

}
