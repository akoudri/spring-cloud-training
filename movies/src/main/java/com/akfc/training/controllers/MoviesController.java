package com.akfc.training.controllers;

import com.akfc.training.configuration.MessagesConfig;
import com.akfc.training.dao.MoviesDAO;
import com.akfc.training.dto.Book;
import com.akfc.training.exceptions.MovieNotFoundException;
import com.akfc.training.model.Movie;
import com.akfc.training.proxies.BookProxy;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
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
public class MoviesController {

    @Autowired
    private MoviesDAO dao;

    @Autowired
    private MessagesConfig messagesConfig;

    @Autowired
    private BookProxy bookProxy;

    @GetMapping("/greetings")
    public String getGreetings() {
        return messagesConfig.getGreetings();
    }

    @GetMapping("/books/{id}")
    @Retry(name = "wait3times2s", fallbackMethod = "getBookFallback")
    @RateLimiter(name = "limit2s")
    @Bulkhead(name = "limit10cc")
    public Book getBook(@PathVariable Long id) {
        log.info("Getting book with id: {}", id);
        return bookProxy.getBook(id);
    }

    public Book getBookFallback(Long id, Throwable t) {
        log.error("Error getting book with id: {}", id, t);
        return null;
    }

    @GetMapping
    public List<Movie> getMovies() {
        return dao.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<Movie> getMovieById(@PathVariable("id") Long id) {
        Movie movie = dao.findById(id).orElse(null);
        if (movie == null) {
            throw new MovieNotFoundException(id);
        }
        EntityModel<Movie> model = EntityModel.of(movie);
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMovies());
        model.add(linkTo.withRel("all-movies"));
        return model;
    }

    @GetMapping("/title/{title}")
    public Movie getMovieByTitle(@PathVariable("title") String title) {
        return dao.findByTitle(title).orElse(null);
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@Valid @RequestBody Movie movie) {
        Movie m = dao.save(movie);
        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(m.getId())
            .toUri();
        return ResponseEntity.created(uri).body(m);
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
