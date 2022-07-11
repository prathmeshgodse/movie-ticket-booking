package com.headstrait.training.movieticketbooking.repositories;

import com.headstrait.training.movieticketbooking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM movie_user WHERE email = :email", nativeQuery = true)
    public void deleteByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM movie_user WHERE email = :email", nativeQuery = true)
    public Optional<User> findByEmail(@Param("email") String email);


}
