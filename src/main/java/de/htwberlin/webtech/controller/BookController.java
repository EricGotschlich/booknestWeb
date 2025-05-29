package de.htwberlin.webtech.controller;

import de.htwberlin.webtech.entity.Book;
import de.htwberlin.webtech.repository.bookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin // Erlaubt Zugriff vom Frontend
@RequestMapping("/books")
public class BookController {

    private final bookRepository repository;

    public BookController(bookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return repository.findAll();
    }
}
