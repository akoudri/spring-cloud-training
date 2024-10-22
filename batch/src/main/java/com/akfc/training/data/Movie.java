package com.akfc.training.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private Long id;

    private String title;

    private String director;

    private Genre genre;

    private LocalDate productionDate;

    private LocalDate releaseDate;

    private String description;

    private Double rating;

    private String fromBook;

}
