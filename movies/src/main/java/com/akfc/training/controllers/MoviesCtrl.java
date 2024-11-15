package com.akfc.training.controllers;

import com.akfc.training.exceptions.MovieNotFoundException;
import com.akfc.training.model.Movie;
import com.akfc.training.services.MoviesService;
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
    private MoviesService movies;

    @GetMapping
    public List<Movie> getMovies() {
        return getMovies();
    }

    @GetMapping("/{id}")
    public EntityModel<Movie> getMovieById(@PathVariable("id") Long id) {
        Movie movie = movies.getMovieById(id);
        if (movie == null) {
            throw new MovieNotFoundException("Movie with id " + id + " not found");
        }
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMovies());
        EntityModel<Movie> movieModel = EntityModel.of(movie);
        movieModel.add(linkTo.withRel("movies"));
        return movieModel;
    }

    @GetMapping("/title/{title}")
    public Movie getMovieByTitle(@PathVariable("title") String title) {
        return movies.getMovieByTitle(title).orElseThrow(() -> new MovieNotFoundException("Movie with title " + title + " not found"));
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@Valid @RequestBody Movie movie) {
        log.info("saving movie with title {}", movie.getTitle());
        Movie m = movies.addMovie(movie);
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
        return movies.updateMovie(movie);
    }

    @DeleteMapping
    public void deleteMovie(@RequestBody Movie movie) {
        movies.deleteMovieById(movie.getId());
    }
}
