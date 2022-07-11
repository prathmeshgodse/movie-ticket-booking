package com.headstrait.training.movieticketbooking.repotests;

import com.headstrait.training.movieticketbooking.models.Ticket;
import com.headstrait.training.movieticketbooking.repositories.TicketRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TicketRepoTest {

    @Autowired
    TicketRepository ticketRepository;

    Ticket testTicket = new Ticket(1L,"A new movie", new Date().getTime(), 1, 22L, 499);

    @BeforeAll
    public void beforeAll() {
        ticketRepository.save(testTicket);
    }

    @AfterAll
    public void afterAll() {
        ticketRepository.deleteById(testTicket.getTicketId());
    }

    @Test
    public void itShouldGetTicketFromRepo() {
        Optional<Ticket> findTicket = ticketRepository.findById(testTicket.getTicketId());
        Assert.assertEquals(testTicket.getMovieName(), findTicket.get().getMovieName());
    }

}
