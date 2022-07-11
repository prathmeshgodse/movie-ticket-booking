package com.headstrait.training.movieticketbooking.servicetests;


import com.headstrait.training.movieticketbooking.exceptions.InvalidSeatCountException;
import com.headstrait.training.movieticketbooking.exceptions.ScreenAlreadyExistsException;
import com.headstrait.training.movieticketbooking.exceptions.ScreenNotFoundException;
import com.headstrait.training.movieticketbooking.models.MovieHall;
import com.headstrait.training.movieticketbooking.models.Seat;
import com.headstrait.training.movieticketbooking.models.SeatCreator;
import com.headstrait.training.movieticketbooking.repositories.MovieHallRepository;
import com.headstrait.training.movieticketbooking.repositories.SeatRepository;
import com.headstrait.training.movieticketbooking.services.MovieHallService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MovieHallServiceTest {

    @Mock
    MovieHallRepository movieHallRepository;

    @Mock
    SeatRepository seatRepository;

    @Mock
    SeatCreator seatCreator;

    @InjectMocks
    MovieHallService movieHallService;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createMovieHall_GivenTotalSeatsAndScreenNumber_CreateMovieHall() throws InvalidSeatCountException, ScreenAlreadyExistsException {
        int screenNumber = 1;
        int totalSeats = 480;
        char rowChar = 'A';
        int col = 12;
        boolean isBooked = false;

        Mockito.when(movieHallRepository.save(new MovieHall(screenNumber,totalSeats))).thenReturn(new MovieHall(screenNumber, totalSeats));
//        Mockito.when(seatRepository.save(new Seat(rowChar,col,isBooked, screenNumber))).thenReturn(new Seat(rowChar,col,isBooked,screenNumber));
        Mockito.doNothing().when(seatCreator).createSeats(screenNumber,totalSeats);
        Mockito.when(movieHallRepository.existsByScreen(screenNumber)).thenReturn(false);
        movieHallService.createHall(screenNumber,totalSeats);
    }

    @Test
    public void createMovieHall_GivenTotalSeatsAndPreExistingScreenNumber_ThrowScreenAlreadyExistsException() throws InvalidSeatCountException, ScreenAlreadyExistsException {
        int screenNumber = 1;
        int totalSeats = 480;
        char rowChar = 'A';
        int col = 12;
        boolean isBooked = false;

        Mockito.when(movieHallRepository.save(new MovieHall(screenNumber,totalSeats))).thenReturn(new MovieHall(screenNumber, totalSeats));
        Mockito.when(seatRepository.save(new Seat(rowChar,col,isBooked, screenNumber))).thenReturn(new Seat(rowChar,col,isBooked,screenNumber));
        Mockito.when(movieHallRepository.existsByScreen(screenNumber)).thenReturn(true);
        Assertions.assertThrows(ScreenAlreadyExistsException.class, () -> movieHallService.createHall(screenNumber, totalSeats));
    }

    @Test
    public void createMovieHall_GivenInvalidTotalSeatCountAndScreenNumber_ThrowInvalidSeatCountException() {
        int screenNumber = 1;
        int totalSeats = 800; //seat count is more than max seat count of 520
        char rowChar = 'A';
        int col = 12;
        boolean isBooked = false;

        Mockito.when(movieHallRepository.save(new MovieHall(screenNumber,totalSeats))).thenReturn(new MovieHall(screenNumber, totalSeats));
        Mockito.when(seatRepository.save(new Seat(rowChar,col,isBooked, screenNumber))).thenReturn(new Seat(rowChar,col,isBooked,screenNumber));
        Mockito.when(movieHallRepository.existsByScreen(screenNumber)).thenReturn(false);
        Assertions.assertThrows(InvalidSeatCountException.class, () -> movieHallService.createHall(screenNumber, totalSeats));
    }

    @Test
    public void deleteMovieHall_GivenScreenNumber_DeleteMovieHall() {
        int screenNumber = 1;
        Mockito.when(movieHallRepository.existsByScreen(screenNumber)).thenReturn(true);
        Mockito.doNothing().when(movieHallRepository).deleteByScreen(screenNumber);
    }

    @Test
    public void deleteMovieHall_GivenInvalidScreenNumber_ThrowMovieHallDoesNotExist() throws ScreenNotFoundException{
        int screenNumber = 1; // invalid screen number
        Mockito.when(movieHallRepository.existsByScreen(screenNumber)).thenReturn(false);
        Assertions.assertThrows(ScreenNotFoundException.class, () -> movieHallRepository.deleteByScreen(screenNumber));
    }
}
