package com.akfc.training.config;

import com.akfc.training.dao.MovieRepository;
import com.akfc.training.data.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final JobRepository jobRepository;
    private final MovieRepository movieRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
        executor.setConcurrencyLimit(10);
        return executor;
    }

    @Bean
    public FlatFileItemReader<Movie> reader() {
        FlatFileItemReader<Movie> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("movies.csv"));
        reader.setName("movieItemReader");
        reader.setLinesToSkip(1);
        reader.setLineMapper(lineMapper());
        return reader;
    }

    @Bean
    public MovieProcessor processor() {
        return new MovieProcessor();
    }

    @Bean
    public RepositoryItemWriter<Movie> writer() {
        RepositoryItemWriter<Movie> writer = new RepositoryItemWriter<>();
        writer.setRepository(movieRepository);
        writer.setMethodName("save");
        return writer;
    }

    private LineMapper<Movie> lineMapper() {
        DefaultLineMapper<Movie> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setQuoteCharacter('"');
        lineTokenizer.setNames("id", "title", "director", "genre", "productionDate", "releaseDate", "description", "rating", "fromBook");
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(new MovieFieldSetMapper());
        return lineMapper;
    }

    @Bean
    public Step importMovieStep() {
        return new StepBuilder("importMovieStep", jobRepository)
                .<Movie, Movie>chunk(5, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job importMovieJob() {
        return new JobBuilder("importMovieJob", jobRepository)
                .start(importMovieStep())
                .build();
    }
}
