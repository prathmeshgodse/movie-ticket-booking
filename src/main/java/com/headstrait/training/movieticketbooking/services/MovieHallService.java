package com.headstrait.training.movieticketbooking.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.headstrait.training.movieticketbooking.exceptions.InvalidSeatCountException;
import com.headstrait.training.movieticketbooking.exceptions.ScreenAlreadyExistsException;
import com.headstrait.training.movieticketbooking.exceptions.ScreenNotFoundException;
import com.headstrait.training.movieticketbooking.models.MovieHall;
import com.headstrait.training.movieticketbooking.models.SeatCreator;
import com.headstrait.training.movieticketbooking.repositories.MovieHallRepository;
import com.headstrait.training.movieticketbooking.repositories.MovieRepository;
import com.headstrait.training.movieticketbooking.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieHallService {

    @Autowired
    MovieHallRepository movieHallRepository;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    SeatCreator seatCreator;


    public void createHall(int screenNumber, int totalSeats) throws ScreenAlreadyExistsException, InvalidSeatCountException {

        //check if screen number exists
        if(movieHallRepository.existsByScreen(screenNumber)) {
            //if it does return ScreenAlreadyExists Exception
            throw new ScreenAlreadyExistsException(screenNumber);
        }
        //check if total seat count is not more than 520
        if(totalSeats > 520) {
            throw new InvalidSeatCountException(totalSeats);
        }

        //if it does not, create a new screen with the seats
        MovieHall movieHall = new MovieHall(screenNumber, totalSeats);
        seatCreator.createSeats(screenNumber, totalSeats);
        movieHallRepository.save(movieHall);
    }

    public void deleteHall(int screenNumber) throws ScreenNotFoundException {
        //check if screen exists
        if(!movieHallRepository.existsByScreen(screenNumber)) {
            //if it does not throw ScreenNotFoundException
            throw new ScreenNotFoundException(screenNumber);
        }
        movieHallRepository.deleteByScreen(screenNumber);
        seatRepository.deleteByScreen(screenNumber);
    }
}
