package com.akfc.training.proxies;

import com.akfc.training.dto.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "books")
public interface BookProxy {

    @GetMapping("/api/books/{id}")
    public Book getBook(@PathVariable Long id);

}
