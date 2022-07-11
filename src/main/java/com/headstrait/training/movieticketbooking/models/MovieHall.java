package com.headstrait.training.movieticketbooking.models;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.headstrait.training.movieticketbooking.repositories.SeatRepository;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "movie_halls")
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
public class MovieHall {
    @Id
    @Column(name = "screen")
    private int screen;

    @Column(name = "total_seats")
    private int totalSeats;

    @Column(name = "available_seat_count")
    private int availableSeatCount;

    public MovieHall(int screen, int totalSeats) {
        this.screen = screen;
        this.totalSeats = totalSeats;
        this.availableSeatCount = totalSeats;
    }
}
