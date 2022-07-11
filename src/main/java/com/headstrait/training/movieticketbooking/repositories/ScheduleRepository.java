package com.headstrait.training.movieticketbooking.repositories;

import com.headstrait.training.movieticketbooking.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
