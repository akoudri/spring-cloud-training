package com.akfc.training.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.akfc.training.data.Movie;
import com.akfc.training.data.MovieRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final MovieRepository movieRepository;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public FlatFileItemReader<Movie> reader() {
        FlatFileItemReader reader = new FlatFileItemReader<Movie>();
        reader.setResource(new ClassPathResource("movies.csv"));
        reader.setName("movieItemReader");
        reader.setLinesToSkip(1);
        reader.setLineMapper(setLineMapper());
        return reader;
    }

    private LineMapper<Movie> setLineMapper() {
        DefaultLineMapper<Movie> lineMapper = new DefaultLineMapper<Movie>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("id", "title", "director", "genre", "productionDate", "releaseDate", "description", "rating", "fromBook");
        tokenizer.setDelimiter(",");
        tokenizer.setStrict(false);
        tokenizer.setQuoteCharacter('"');
        BeanWrapperFieldSetMapper<Movie> fieldSetMapper = new BeanWrapperFieldSetMapper<Movie>();
        fieldSetMapper.setTargetType(Movie.class);
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    public MovieProcessor processor() {
        return new MovieProcessor();
    }

    @Bean
    public RepositoryItemWriter<Movie> writer() {
        RepositoryItemWriter<Movie> writer = new RepositoryItemWriter<Movie>();
        writer.setRepository(movieRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step importMovieStep() {
        return new StepBuilder("importMovieStep", jobRepository)
                .<Movie, Movie>chunk(10, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job importMovieJob() {
        return new JobBuilder("importMovieJob", jobRepository)
                .start(importMovieStep())
                .build();
    }

}
