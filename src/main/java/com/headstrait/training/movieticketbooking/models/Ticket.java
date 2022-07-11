package com.headstrait.training.movieticketbooking.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ticketId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "movie_time_and_date")
    private Long movieTimeAndDate;

    @Column(name = "screen")
    private int screenNumber;

    @Column(name = "seat_id")
    private long seatId;

    @Column(name = "price")
    private int price;


    public Ticket(Long userId, String movieName, Long movieTimeAndDate, int screenNumber, long seatId, int price) {
        this.userId = userId;
        this.movieName = movieName;
        this.movieTimeAndDate = movieTimeAndDate;
        this.screenNumber = screenNumber;
        this.seatId = seatId;
        this.price = price;
    }
}
