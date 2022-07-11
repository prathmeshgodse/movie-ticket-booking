package com.headstrait.training.movieticketbooking.repotests;

import com.headstrait.training.movieticketbooking.models.Movie;
import com.headstrait.training.movieticketbooking.models.User;
import com.headstrait.training.movieticketbooking.repositories.MovieRepository;
import com.headstrait.training.movieticketbooking.repositories.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MovieRepoTest {


    @Autowired
    MovieRepository movieRepository;

    Movie testMovie = new Movie("testmovie", 120);
    Movie oldMovie = new Movie("Old Movie Name", 180);


    @BeforeAll
    public void beforeAll() {
        movieRepository.save(testMovie);
        movieRepository.save(oldMovie);
    }

    @AfterAll
    public void afterAll() {
        movieRepository.deleteByMovieName(testMovie.getMovieName());
        movieRepository.deleteById(oldMovie.getMovieId());
    }

    @Test
    public void itShouldReturnAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        Assert.assertEquals(1, movies.size());
    }

    @Test
    public void itShouldSaveMovie() {
        Movie movie = new Movie("savemovie", 187);
        movieRepository.save(movie);
        Optional<Movie> foundMovie = movieRepository.findByMovieName(movie.getMovieName());
        Assert.assertEquals(movie.getMovieName(),foundMovie.get().getMovieName());
    }

    @Test
    public void itShouldDeleteMovie() {
        Movie movie = new Movie("deletemovie", 245);
        movieRepository.save(movie);
        movieRepository.deleteByMovieName(movie.getMovieName());
        Optional<Movie> checkMovie = movieRepository.findByMovieName(movie.getMovieName());
        Assert.assertEquals(Optional.empty(), checkMovie);
    }

    @Test
    public void itShouldGetMovie() {
        Optional<Movie> findMovie = movieRepository.findByMovieName(testMovie.getMovieName());
        Assert.assertEquals(testMovie.getMovieName(), findMovie.get().getMovieName() );
    }

    @Test
    public void itShouldUpdateMovie() {
        String newMovieName = "New Movie";
        int duration = 380;
        movieRepository.updateMovieById(newMovieName,duration, oldMovie.getMovieId());
        Assert.assertEquals(newMovieName, movieRepository.findById(oldMovie.getMovieId()).get().getMovieName());
    }
}
