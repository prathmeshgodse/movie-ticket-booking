package com.headstrait.training.movieticketbooking.models;


import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieUpdateRequest {
    private String nameOfMovieToUpdate;
    private String newMovieName;

    private int duration;

    public MovieUpdateRequest(String nameOfMovieToUpdate, String newMovieName) {
        this.nameOfMovieToUpdate = nameOfMovieToUpdate;
        this.newMovieName = newMovieName;
    }

    public MovieUpdateRequest(String nameOfMovieToUpdate, int duration) {
        this.nameOfMovieToUpdate = nameOfMovieToUpdate;
        this.duration = duration;
    }
}
