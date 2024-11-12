package com.akfc.training.config;

import com.akfc.training.data.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class MovieProcessor implements ItemProcessor<Movie, Movie> {

    @Override
    public Movie process(Movie movie) throws Exception {
        log.info("Processing movie: {}", movie);
        return movie;
    }
}
