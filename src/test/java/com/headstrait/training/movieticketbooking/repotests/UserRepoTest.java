package com.headstrait.training.movieticketbooking.repotests;

import com.headstrait.training.movieticketbooking.models.User;
import com.headstrait.training.movieticketbooking.repositories.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepoTest {

    @Autowired
    UserRepository userRepository;

    User testUser = new User("username","email", "password");

    @BeforeAll
    public void beforeAll() {
        userRepository.save(testUser);
    }

    @AfterAll
    public void afterAll() {
        userRepository.deleteByEmail(testUser.getEmail());
        userRepository.deleteByEmail("saveemail");
    }

    @Test
    public void itShouldReturnAllUsers() {
        List<User> users = userRepository.findAll();
        Assert.assertEquals(1, users.size());
    }

    @Test
    public void itShouldSaveUser() {
        User user = new User("saveuser", "saveemail", "savepassword");
        userRepository.save(user);
        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());
        Assert.assertEquals(user.getUsername(),foundUser.get().getUsername());
    }

    @Test
    public void itShouldDeleteUser() {
        User user = new User("deleteuser","deleteemail","deletepassword");
        userRepository.save(user);
        userRepository.deleteByEmail(user.getEmail());
        Optional<User> checkUser = userRepository.findByEmail(user.getEmail());
        Assert.assertEquals(Optional.empty(), checkUser);
    }

    @Test
    public void itShouldGetUser() {
        User user = new User("getusername", "getuseremail", "getuserpassword");
        userRepository.save(user);
        Optional<User> findUser = userRepository.findByEmail(user.getEmail());
        Assert.assertEquals(user.getUsername(), findUser.get().getUsername() );
    }
}
