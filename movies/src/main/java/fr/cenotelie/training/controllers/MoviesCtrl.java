package fr.cenotelie.training.controllers;

import fr.cenotelie.training.configuration.MessagesConfig;
import fr.cenotelie.training.dao.MoviesDAO;
import fr.cenotelie.training.dto.Book;
import fr.cenotelie.training.exceptions.MovieNotFoundException;
import fr.cenotelie.training.model.Movie;
import fr.cenotelie.training.proxies.BookProxy;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/movies")
public class MoviesCtrl {

    @Autowired
    private MessagesConfig config;

    @Autowired
    private MoviesDAO dao;

    @Autowired
    private BookProxy proxy;

    @GetMapping
    public List<Movie> getMovies() {
        return dao.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<Movie> getMovieById(@PathVariable("id") Long id) {
        Movie movie = dao.findById(id).orElse(null);
        if (movie == null) {
            throw new MovieNotFoundException("Movie with id " + id + " not found");
        }
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMovies());
        EntityModel<Movie> movieModel = EntityModel.of(movie);
        movieModel.add(linkTo.withRel("movies"));
        return movieModel;
    }

    @GetMapping("/message")
    public String getMessage() {
        return config.getMessage();
    }

    @GetMapping("/{id}/book")
    @CircuitBreaker(name = "wait3times2s", fallbackMethod = "fallbackGetBook")
    @RateLimiter(name = "wait3times2s")
    @Bulkhead(name = "wait3times2s")
    //@Retry(name = "wait3times2s", fallbackMethod = "fallbackGetBook")
    public Book getMoviesByBook(@PathVariable("id") Long id) {
        Movie movie = dao.findById(id).orElse(null);
        if (movie == null) {
            throw new MovieNotFoundException("Movie with id " + id + " not found");
        }
        if (movie.getFromBook() == null) return null;
        //RestTemplate restTemplate = new RestTemplate();
        //return restTemplate.getForObject(movie.getFromBook(), Book.class);
        return proxy.getBookByTitle(movie.getFromBook());
    }

    public Book fallbackGetBook(Long id) {
        return null;
    }

    @GetMapping("/title/{title}")
    public Movie getMovieByTitle(@PathVariable("title") String title) {
        return dao.findByTitle(title).orElse(null);
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@Valid @RequestBody Movie movie) {
        log.info("saving movie with title {}", movie.getTitle());
        Movie m = dao.save(movie);
        URI location =
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(m.getId())
                        .toUri();
        return ResponseEntity.created(location).body(m);
    }

    @PutMapping
    public Movie updateMovie(@Valid @RequestBody Movie movie) {
        return dao.save(movie);
    }

    @DeleteMapping
    public void deleteMovie(@RequestBody Movie movie) {
        dao.delete(movie);
    }
}
