package com.headstrait.training.movieticketbooking.repotests;

import com.headstrait.training.movieticketbooking.models.Schedule;
import com.headstrait.training.movieticketbooking.repositories.ScheduleRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ScheduleRepoTest {

    @Autowired
    ScheduleRepository scheduleRepository;

    Schedule testSchedule = new Schedule(1L, 123123L);

    @BeforeAll
    public void beforeAll() {
        scheduleRepository.save(testSchedule);
    }

    @AfterAll
    public void afterAll() {
        scheduleRepository.deleteById(testSchedule.getScheduleId());
    }

    @Test
    public void itShouldGetScheduleIfItExists() {
        Optional<Schedule> findSchedule = scheduleRepository.findById(testSchedule.getScheduleId());
        Assert.assertEquals(testSchedule.getDateAndTime(), findSchedule.get().getDateAndTime());
    }

    @Test
    public void itShouldDeleteSchduleIfItExists() {
        Schedule deleteSchedule = new Schedule(1L, 392130L);
        scheduleRepository.save(deleteSchedule);
        scheduleRepository.deleteById(deleteSchedule.getScheduleId());
        Assert.assertFalse(scheduleRepository.existsById(deleteSchedule.getScheduleId()));
    }
}
