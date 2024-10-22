package com.akfc.training.config;

import com.akfc.training.data.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    public FlatFileItemReader<Movie> reader() {
        FlatFileItemReader<Movie> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("movies.csv"));
        return null;
    }

}
