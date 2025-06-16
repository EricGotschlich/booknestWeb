package de.htwberlin.webtech.controller;

import de.htwberlin.webtech.entity.Book;
import de.htwberlin.webtech.service.BookService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin // Erlaubt Zugriff vom Frontend
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Book>> getAllBooks() {
        final Iterable<Book> result = bookService.getAllBooks();
        return ResponseEntity.ok(result);
    }
    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody final Book book) {
        final Book created = bookService.addBook(book);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
