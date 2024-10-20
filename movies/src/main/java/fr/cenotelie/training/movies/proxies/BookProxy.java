package fr.cenotelie.training.movies.proxies;

import fr.cenotelie.training.movies.dto.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "books")
public interface BookProxy {

    @GetMapping("/books/title")
    Book getBookByTitle(@RequestParam("title") String title);

}
