package com.akfc.training.config;

import com.akfc.training.data.Genre;
import com.akfc.training.data.Movie;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.time.ZoneId;

public class MovieFieldSetMapper implements FieldSetMapper<Movie> {

    @Override
    public Movie mapFieldSet(FieldSet fieldSet) throws BindException {
        return Movie.builder()
                .id(fieldSet.readLong("id"))
                .title(fieldSet.readString("title"))
                .director(fieldSet.readString("director"))
                .genre(Genre.values()[fieldSet.readInt("genre")])
                .productionDate(fieldSet.readDate("productionDate", "yyyy-MM-dd").toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .releaseDate(fieldSet.readDate("releaseDate", "yyyy-MM-dd").toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .description(fieldSet.readString("description"))
                .rating(fieldSet.readDouble("rating"))
                .fromBook(fieldSet.readString("fromBook"))
                .build();
    }
}
