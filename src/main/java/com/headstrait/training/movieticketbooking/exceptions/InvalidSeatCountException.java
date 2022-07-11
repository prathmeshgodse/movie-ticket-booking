package com.headstrait.training.movieticketbooking.exceptions;

public class InvalidSeatCountException extends Exception{
    public InvalidSeatCountException(int seatCount) {
        super(String.format("Invalid Seat Count. Seat Count cannot exceed 520. Seat Count received: %s", seatCount));
    }
}
