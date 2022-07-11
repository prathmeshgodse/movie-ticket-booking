package com.headstrait.training.movieticketbooking.repositories;


import com.headstrait.training.movieticketbooking.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
