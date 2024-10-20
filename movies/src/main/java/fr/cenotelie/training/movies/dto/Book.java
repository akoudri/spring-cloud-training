package fr.cenotelie.training.movies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String description;
    private String author;
    private String isbn;
    private LocalDate releaseDate;
    private Double price;
}
