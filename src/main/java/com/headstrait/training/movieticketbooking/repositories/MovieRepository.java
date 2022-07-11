package com.headstrait.training.movieticketbooking.repositories;

import com.headstrait.training.movieticketbooking.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "SELECT * FROM movies WHERE movie_name = :movie_name", nativeQuery = true)
    public Optional<Movie> findByMovieName(@Param("movie_name") String movieName);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM movies WHERE movie_name = :movie_name", nativeQuery = true)
    public void deleteByMovieName(@Param("movie_name") String movieName);

    public boolean existsMovieByMovieName(String movie_name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE movies SET movie_name = :new_movie_name, duration = :duration WHERE movie_id = :movie_id", nativeQuery = true)
    public void updateMovieById(@Param("new_movie_name") String movie_name,
                                @Param("duration") int duration,
                                @Param("movie_id") Long movieId);
}
