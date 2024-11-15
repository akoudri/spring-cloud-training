package com.akfc.training.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT") //Specific to postgresql
    private String description;
    @Column(nullable = false)
    private String isbn;
    @Column(nullable = false)
    @Past
    private LocalDate releaseDate;
    private Double price;
    @Min(0)
    @Max(5)
    private Double rating;
}
