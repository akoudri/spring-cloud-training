package com.akfc.training.dao;

import com.akfc.training.model.Movie;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface MoviesDAO extends JpaRepository<Movie, Long> {

    Optional<Movie> findByTitle(String title);
}
