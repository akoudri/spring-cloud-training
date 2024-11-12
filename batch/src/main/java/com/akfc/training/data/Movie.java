package com.akfc.training.data;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title", "director"})
})
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String director;
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Genre genre;
    @Column(nullable = false)
    private LocalDate productionDate;
    @Column(nullable = false)
    private LocalDate releaseDate;
    @Column(columnDefinition = "TEXT") //Specific to postgresql
    private String description;
    private Double rating;
    private String fromBook;
}

