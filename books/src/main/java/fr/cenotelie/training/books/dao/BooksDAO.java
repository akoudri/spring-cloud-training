package fr.cenotelie.training.books.dao;

import fr.cenotelie.training.books.model.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface BooksDAO extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
}
