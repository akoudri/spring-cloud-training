package fr.cenotelie.training.books.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @Past
    private LocalDate birthdate;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;
}
