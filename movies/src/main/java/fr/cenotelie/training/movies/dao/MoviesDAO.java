package fr.cenotelie.training.movies.dao;

import fr.cenotelie.training.movies.model.Movie;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface MoviesDAO extends JpaRepository<Movie, Long> {

    @Cacheable
    Optional<Movie> findByTitle(String title);
}
