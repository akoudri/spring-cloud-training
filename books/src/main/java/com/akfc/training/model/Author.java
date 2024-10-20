package com.akfc.training.model;

import java.time.LocalDate;
import java.util.List;

public class Author {
    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private List<Book> books;

    public Author() {
    }

    public Author(Long id, String firstname, String lastname, LocalDate birthdate, List<Book> books) {
        if (firstname == null || firstname.isBlank()) {
            throw new IllegalArgumentException("Firstname cannot be blank");
        }
        if (lastname == null || lastname.isBlank()) {
            throw new IllegalArgumentException("Lastname cannot be blank");
        }
        if (birthdate == null || birthdate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birthdate must be in the past");
        }
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        if (firstname == null || firstname.isBlank()) {
            throw new IllegalArgumentException("Firstname cannot be blank");
        }
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        if (lastname == null || lastname.isBlank()) {
            throw new IllegalArgumentException("Lastname cannot be blank");
        }
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        if (birthdate == null || birthdate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birthdate must be in the past");
        }
        this.birthdate = birthdate;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}