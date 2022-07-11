package com.headstrait.training.movieticketbooking.repositories;

import com.headstrait.training.movieticketbooking.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM seats WHERE screen_number = :screen", nativeQuery = true)
    public void deleteByScreen(@Param("screen") int screen);
}
