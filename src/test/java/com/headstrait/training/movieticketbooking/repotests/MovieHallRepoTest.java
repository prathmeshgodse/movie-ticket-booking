package com.headstrait.training.movieticketbooking.repotests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.headstrait.training.movieticketbooking.models.MovieHall;
import com.headstrait.training.movieticketbooking.models.SeatCreator;
import com.headstrait.training.movieticketbooking.repositories.MovieHallRepository;
import com.headstrait.training.movieticketbooking.repositories.SeatRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MovieHallRepoTest {

    @Autowired
    MovieHallRepository movieHallRepository;

    MovieHall testMovieHall = new MovieHall(1, 300);

    @Autowired
    SeatCreator seatCreator;

    @Autowired
    SeatRepository seatRepository;

    public MovieHallRepoTest() throws JsonProcessingException {
    }

    @BeforeAll
    public void beforeAll() {
        movieHallRepository.save(testMovieHall);
        seatCreator.createSeats(testMovieHall.getScreen(), testMovieHall.getTotalSeats());
    }

    @AfterAll
    public void afterAll() {
        movieHallRepository.deleteById(testMovieHall.getScreen());
        seatRepository.deleteByScreen(testMovieHall.getScreen());
    }

    @Test
    public void itShouldReturnMovieHall() {
        Optional<MovieHall> movieHall = movieHallRepository.findById(testMovieHall.getScreen());
        Assert.assertEquals(testMovieHall.getTotalSeats(), movieHall.get().getTotalSeats());
    }

    @Test
    public void itShouldDeleteMovieHall() throws JsonProcessingException {
        MovieHall movieHall = new MovieHall(2, 280);
        movieHallRepository.save(movieHall);
        movieHallRepository.deleteByScreen(movieHall.getScreen());
        Optional<MovieHall> findMovieHall = movieHallRepository.getByScreen(movieHall.getScreen());
        Assert.assertEquals(Optional.empty(), findMovieHall);
    }


}
