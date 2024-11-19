package com.akfc.training.proxies;

import com.akfc.training.dto.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "books")
public interface BookProxy {

    @GetMapping("/api/books/{id}")
    Book getBook(@PathVariable Long id) throws InterruptedException;

}
