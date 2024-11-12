package com.akfc.training.controllers;

import com.akfc.training.model.Book;
import com.akfc.training.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksController {
    @Autowired
    private BooksService booksService;

    @GetMapping
    public List<Book> getBooks() {
        return booksService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) throws InterruptedException {
        Thread.sleep(4000);
        return booksService.getBookById(id);
    }

    @GetMapping("/title")
    public Book getBookByTitle(@RequestParam("title") String title) {
        return booksService.getBookByTitle(title);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return booksService.addBook(book);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        return booksService.updateBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        booksService.deleteBookById(id);
    }

}
