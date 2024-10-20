package fr.cenotelie.training.movies.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @Email
    private String email;
    @Past
    private LocalDate birthdate;
    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;
}
