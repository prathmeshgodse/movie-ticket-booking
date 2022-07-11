package com.headstrait.training.movieticketbooking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie_schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long scheduleId;

    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "date_and_time")
    private Long dateAndTime;

    public Schedule(Long movieId, Long dateAndTime) {
        this.movieId = movieId;
        this.dateAndTime = dateAndTime;
    }
}
