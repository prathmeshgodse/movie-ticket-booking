package com.headstrait.training.movieticketbooking.servicetests;

import com.headstrait.training.movieticketbooking.exceptions.MovieAlreadyExistsException;
import com.headstrait.training.movieticketbooking.exceptions.MovieNotFoundException;
import com.headstrait.training.movieticketbooking.models.Movie;
import com.headstrait.training.movieticketbooking.models.MovieUpdateRequest;
import com.headstrait.training.movieticketbooking.repositories.MovieRepository;
import com.headstrait.training.movieticketbooking.services.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MovieServiceTest {

    @Mock
    MovieRepository mockMovieRepository;

    @InjectMocks
    MovieService movieService;


    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addMovie_GivenValidMovie_AddMovie() throws MovieAlreadyExistsException {
        String movieName = "Test Movie";
        int duration = 184;
        Movie testMovie = new Movie(movieName, duration);
        Mockito.when(mockMovieRepository.save(testMovie)).thenReturn(testMovie);
        Mockito.when(mockMovieRepository.existsMovieByMovieName(movieName)).thenReturn(false);
        movieService.addMovie(movieName, duration);
    }

    @Test
    public void addMovie_GivenDuplicateMovie_ThrowMovieAlreadyExistsException() throws MovieAlreadyExistsException {
        String movieName = "Test Movie";
        int duration = 184;
        Movie testMovie = new Movie(movieName, duration);
        Mockito.when(mockMovieRepository.save(testMovie)).thenReturn(testMovie);
        Mockito.when(mockMovieRepository.existsMovieByMovieName(movieName)).thenReturn(true);
        Assertions.assertThrows(MovieAlreadyExistsException.class, () -> movieService.addMovie(movieName,duration));
    }

    @Test
    public void deleteMovie_GivenValidMovieName_DeleteMovie() throws MovieNotFoundException {
        String movieName = "Delete Movie";
        Mockito.doNothing().when(mockMovieRepository).deleteByMovieName(movieName);
        Mockito.when(mockMovieRepository.existsMovieByMovieName(movieName)).thenReturn(true);
        movieService.deleteMovie(movieName);
    }

    @Test
    public void deleteMovie_GivenNonExistingMovieName_ThrowMovieNotFoundException() throws MovieNotFoundException {
        String movieName = "Delete Movie 2";
        Mockito.when(mockMovieRepository.existsMovieByMovieName(movieName)).thenReturn(false);
        Mockito.doNothing().when(mockMovieRepository).deleteByMovieName(movieName);
        Assertions.assertThrows(MovieNotFoundException.class, () -> movieService.deleteMovie(movieName));
    }

    @Test
    public void getMovie_GivenValidMovieName_GetMovie() {
        String movieName = "Find Movie";
        int duration = 175;
        Movie findMovie = new Movie(movieName, duration);
        Mockito.when(mockMovieRepository.existsMovieByMovieName(movieName)).thenReturn(true);
        Mockito.when(mockMovieRepository.findByMovieName(movieName)).thenReturn(Optional.of(findMovie));
    }

    @Test
    public void getMovie_GivenNonExistentMovieName_ThrowMovieDoesNotExistException() {
        String movieName = "Find Movie";
        int duration = 175;
        Mockito.when(mockMovieRepository.existsMovieByMovieName(movieName)).thenReturn(false);
        Assertions.assertThrows(MovieNotFoundException.class, () -> movieService.findMovie(movieName));
    }

    @Test
    public void updateMovie_GivenValidMovieToUpdateAndUpdateData_UpdateMovie() throws MovieNotFoundException, MovieAlreadyExistsException {
        String oldMovieName = "Old Movie";
        String newMovieName = "New Movie";
        int duration = 200;
        int oldDuration = 180;
        Movie oldMovie = new Movie(oldMovieName, oldDuration);
        Mockito.when(mockMovieRepository.existsMovieByMovieName(oldMovieName)).thenReturn(true);
        Mockito.when(mockMovieRepository.existsMovieByMovieName(newMovieName)).thenReturn(false);
        Mockito.when(mockMovieRepository.findByMovieName(oldMovieName).get()).thenReturn(oldMovie);
        Mockito.when(mockMovieRepository.findByMovieName(oldMovieName).get().getMovieId()).thenReturn(1L);
        MovieUpdateRequest movieUpdateRequest = new MovieUpdateRequest(oldMovieName, newMovieName, duration);
        movieService.updateMovie(movieUpdateRequest);
    }

    @Test
    public void updateMovie_GivenValidMovieToUpdateWithDuplicateMovieDataToUpdate_ThrowMovieAlreadyExistsException() throws MovieNotFoundException, MovieAlreadyExistsException {
        String oldMovieName = "Old Movie";
        String newMovieName = "New Movie";
        int duration = 200;
        Movie oldMovie = new Movie(oldMovieName, duration);
        MovieUpdateRequest movieUpdateRequest = new MovieUpdateRequest(oldMovieName, newMovieName, duration);
        Mockito.when(mockMovieRepository.existsMovieByMovieName(oldMovieName)).thenReturn(true);
        Mockito.when(mockMovieRepository.existsMovieByMovieName(newMovieName)).thenReturn(true);
        Mockito.when(mockMovieRepository.findByMovieName(oldMovieName).get()).thenReturn(oldMovie);
        Mockito.when(mockMovieRepository.findByMovieName(oldMovieName).get().getMovieId()).thenReturn(1L);
        Assertions.assertThrows(MovieAlreadyExistsException.class, () -> movieService.updateMovie(movieUpdateRequest));
    }

    @Test
    public void updateMovie_GivenInvalidMovieToUpdate_ThrowMovieNotFoundException() {
        String oldMovieName = "Old Movie";
        String newMovieName = "New Movie";
        int duration = 200;
        MovieUpdateRequest movieUpdateRequest = new MovieUpdateRequest(oldMovieName, newMovieName, duration);
        Mockito.when(mockMovieRepository.existsMovieByMovieName(oldMovieName)).thenReturn(false);
        Mockito.when(mockMovieRepository.existsMovieByMovieName(newMovieName)).thenReturn(true);
        Assertions.assertThrows(MovieNotFoundException.class, () -> movieService.updateMovie(movieUpdateRequest));
    }

}
