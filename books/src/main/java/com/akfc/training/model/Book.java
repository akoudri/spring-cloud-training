package com.akfc.training.model;

import java.time.LocalDate;

public class Book {
    private Long id;
    private String title;
    private String description;
    private String isbn;
    private LocalDate releaseDate;
    private Double price;
    private Double rating;
    private Author author;

    public Book() {
    }

    public Book(Long id, String title, String description, String isbn, LocalDate releaseDate, Double price, Double rating, Author author) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be blank");
        }
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN cannot be blank");
        }
        if (releaseDate == null || releaseDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Release date must be in the past");
        }
        if (rating != null && (rating < 0 || rating > 5)) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        this.id = id;
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.releaseDate = releaseDate;
        this.price = price;
        this.rating = rating;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be blank");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN cannot be blank");
        }
        this.isbn = isbn;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        if (releaseDate == null || releaseDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Release date must be in the past");
        }
        this.releaseDate = releaseDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        if (rating != null && (rating < 0 || rating > 5)) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        this.rating = rating;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}