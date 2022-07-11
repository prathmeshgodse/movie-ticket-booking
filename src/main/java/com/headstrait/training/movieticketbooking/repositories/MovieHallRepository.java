package com.headstrait.training.movieticketbooking.repositories;

import com.headstrait.training.movieticketbooking.models.MovieHall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface MovieHallRepository extends JpaRepository<MovieHall, Integer> {

    public boolean existsByScreen(int ScreenNumber);

    @Modifying
    @Transactional
    public void deleteByScreen(int screenNumber);

    public Optional<MovieHall> getByScreen(int ScreenNumber);

}
