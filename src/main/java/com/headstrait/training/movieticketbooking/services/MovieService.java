package com.headstrait.training.movieticketbooking.services;

import com.headstrait.training.movieticketbooking.exceptions.MovieAlreadyExistsException;
import com.headstrait.training.movieticketbooking.exceptions.MovieNotFoundException;
import com.headstrait.training.movieticketbooking.models.Movie;
import com.headstrait.training.movieticketbooking.models.MovieUpdateRequest;
import com.headstrait.training.movieticketbooking.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public Optional<Movie> findMovie(String movieName) throws MovieNotFoundException {
        if(movieRepository.existsMovieByMovieName(movieName)) {
        return movieRepository.findByMovieName(movieName);
        } else {
            throw new MovieNotFoundException(movieName);
        }
    }

    public void addMovie(String movieName, int duration) throws MovieAlreadyExistsException {
        //check if movie Exists
        if(movieRepository.existsMovieByMovieName(movieName)) {
            //if it does throw exception
            throw new MovieAlreadyExistsException(movieName);
        }

        //if it does not
        Movie movie = new Movie(movieName, duration);
        movieRepository.save(movie);
    }

    public void deleteMovie(String movieName) throws MovieNotFoundException {
        //check if movie exists
        if(!movieRepository.existsMovieByMovieName(movieName)) {
            //if it does not throw exception
            throw new MovieNotFoundException(movieName);
        }
        //if it does delete it
        movieRepository.deleteByMovieName(movieName);
    }

    public void updateMovie(MovieUpdateRequest movieUpdateRequest) throws MovieNotFoundException, MovieAlreadyExistsException {
        //check if movie exists
        if(!movieRepository.existsMovieByMovieName(movieUpdateRequest.getNameOfMovieToUpdate())) {
            //if it does not throw exception
            throw new MovieNotFoundException(movieUpdateRequest.getNameOfMovieToUpdate());
        }
        //check if new movie name (if provided) already exists
        if(!movieUpdateRequest.getNewMovieName().isEmpty() && movieRepository.existsMovieByMovieName(movieUpdateRequest.getNewMovieName())) {
            throw new MovieAlreadyExistsException(movieUpdateRequest.getNewMovieName());
        }

        Long idOfMovieToUpdate = movieRepository.findByMovieName(movieUpdateRequest.getNameOfMovieToUpdate()).get().getMovieId();
        movieRepository.updateMovieById(movieUpdateRequest.getNewMovieName(),movieUpdateRequest.getDuration(), idOfMovieToUpdate);
    }
}
