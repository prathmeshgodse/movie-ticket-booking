package com.headstrait.training.movieticketbooking.models;

import com.headstrait.training.movieticketbooking.repositories.SeatRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private long seatId;

    @Column(name = "row")
    private char row;

    @Column(name = "col")
    private int column;

    @Column(name = "is_booked")
    private boolean isBooked;

    @Column(name = "screen_number")
    private int screenNumber;

    public Seat(char row, int column, boolean isBooked, int screenNumber) {
        this.row = row;
        this.column = column;
        this.isBooked = isBooked;
        this.screenNumber = screenNumber;
    }

}
