package fr.cenotelie.training.books.controllers;

import fr.cenotelie.training.books.dao.BooksDAO;
import fr.cenotelie.training.books.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BooksDAO dao;

    @GetMapping
    public List<Book> getBooks() {
        return dao.findAll();
    }
    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return dao.findById(id).orElse(null);
    }

    @GetMapping("/title")
    public Book getBookByTitle(@RequestParam("title") String title) {
        return dao.findByTitle(title).orElse(null);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return dao.save(book);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        return dao.save(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        dao.deleteById(id);
    }

}
