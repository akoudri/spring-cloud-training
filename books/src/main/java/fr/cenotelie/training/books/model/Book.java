package fr.cenotelie.training.books.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@MappedSuperclass => doesn't work in case of direct reference
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;
}
