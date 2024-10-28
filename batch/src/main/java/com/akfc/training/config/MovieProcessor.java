package com.akfc.training.config;

import org.springframework.batch.item.ItemProcessor;

import com.akfc.training.data.Movie;

public class MovieProcessor implements ItemProcessor<Movie, Movie> {

    @Override
    public Movie process(Movie movie) throws Exception {
        return movie;
    }

}
