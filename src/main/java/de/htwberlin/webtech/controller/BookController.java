package de.htwberlin.webtech.controller;

import de.htwberlin.webtech.entity.Book;
import de.htwberlin.webtech.repository.bookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = {"http://localhost:5173", "https://booknestfrontend-iaq4.onrender.com"})

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
