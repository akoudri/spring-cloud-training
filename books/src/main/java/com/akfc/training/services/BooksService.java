package com.akfc.training.services;

import com.akfc.training.dao.BooksDAO;
import com.akfc.training.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {

    private final BooksDAO booksDAO;

    public BooksService(BooksDAO booksDAO) {
        this.booksDAO = booksDAO;
    }

    //@Cacheable(value = "books", key = "#result.id")
    public List<Book> getAllBooks() {
        return booksDAO.findAll();
    }

    //@CachePut(value = "books", key = "#result.title")
    public Book addBook(Book book) {
        return booksDAO.save(book);
    }

    //@Cacheable(value = "books", key = "#title")
    public Book getBookByTitle(String title) {
        return booksDAO.findByTitle(title).orElse(null);
    }

    //@Cacheable(value = "books", key = "#id")
    public Book getBookById(long id) {
        return booksDAO.findById(id).orElse(null);
    }

    //@CachePut(value = "books", key = "#title")
    public Book updateBook(Book book) {
        return booksDAO.save(book);
    }

    //@CacheEvict(value = "books", key = "#id")
    public void deleteBookById(long id) {
        booksDAO.deleteById(id);
    }

}
