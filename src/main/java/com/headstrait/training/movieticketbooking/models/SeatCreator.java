package com.headstrait.training.movieticketbooking.models;


import com.headstrait.training.movieticketbooking.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Component
public class SeatCreator {

    @Autowired
    private SeatRepository seatRepository;

    public void createSeats(int screenNumber, int totalSeats) {
        //each row will have 20 seats. rows will be assigned letters from A - Z
        //Assuming there will be no more than 520 seats in a single hall, max of 26 rows, each with 20 seats
        //columns will be seat numbers. 1 - 20
        int rows = totalSeats/20;
        for(int row = 1; row <= rows; row++) {
            for(int col = 1; col <= 20; col++) {
                char rowChar = (char)(64 + row);
                Seat seat = new Seat(rowChar, col, false, screenNumber);
                seatRepository.save(seat);
            }
        }
    }

}
