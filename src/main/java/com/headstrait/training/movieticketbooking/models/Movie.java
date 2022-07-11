package com.headstrait.training.movieticketbooking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue
    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "movie_name", unique = true)
    private String movieName;

    @Column(name = "duration")
    private int duration;

    public Movie(String movieName, int duration) {
        this.movieName = movieName;
        this.duration = duration;
    }
}
