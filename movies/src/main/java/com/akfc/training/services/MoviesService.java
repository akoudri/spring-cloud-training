package com.akfc.training.services;

import com.akfc.training.dao.MoviesDAO;
import com.akfc.training.model.Movie;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoviesService {

    private final MoviesDAO moviesDAO;

    public MoviesService(MoviesDAO moviesDAO) {
        this.moviesDAO = moviesDAO;
    }

    @Cacheable(value = "movies", key = "#result.id")
    public List<Movie> getAllMovies() {
        return moviesDAO.findAll();
    }

    @Cacheable(value = "movies", key = "#id")
    public Movie getMovieById(long id) {
        return moviesDAO.getReferenceById(id);
    }

    @Cacheable(value = "movies", key = "#title")
    public Optional<Movie> getMovieByTitle(String title) {
        return moviesDAO.findByTitle(title);
    }

    @CachePut(value = "movies", key = "#result.id")
    public Movie addMovie(Movie movie) {
        return moviesDAO.save(movie);
    }

    @CachePut(value = "movies", key = "#result.id")
    public Movie updateMovie(Movie movie) {
        return moviesDAO.save(movie);
    }

    @CacheEvict(value = "movies", key = "#id")
    public void deleteMovieById(long id) {
        moviesDAO.deleteById(id);
    }

}
