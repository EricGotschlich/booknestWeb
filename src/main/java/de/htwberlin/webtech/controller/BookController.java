package de.htwberlin.webtech.controller;

import de.htwberlin.webtech.entity.Book;
import de.htwberlin.webtech.service.BookService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin 
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

    @GetMapping(value = "/genre/{genre}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Book>> getBooksByGenre(@PathVariable("genre") String genre) {
        final Iterable<Book> result = bookService.getBooksByGenre(genre);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/title/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Book>> getBooksByTitle(@PathVariable("title") String title) {
        final Iterable<Book> result = bookService.getBooksByTitle(title);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") final Long id) {
        final Optional<Book> bookOptional = bookService.getBookById(id);
        if (!bookOptional.isPresent()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(bookOptional.get());
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody final Book book) {
        final Book created = bookService.addBook(book);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        book.setId(id);
        Book updated = bookService.editBook(book);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/progress/{id}")
    public ResponseEntity<Book> updateReadingProgress(@PathVariable("id") Long id, @RequestParam(required = false) String progress) {
        if (progress == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Optional<Book> optionalBook = bookService.getBookById(id);
        if (!optionalBook.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Book book = optionalBook.get();
        book.setReadingProgress(progress);

        Book updatedBook = bookService.editBook(book);
        return ResponseEntity.ok(updatedBook);
    }

    @GetMapping(value = "/favorites", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Book>> getFavoriteBooks() {
        final Iterable<Book> result = bookService.getFavoriteBooks();
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/favorite/{id}")
    public ResponseEntity<Book> updateFavorite(@PathVariable("id") Long id, @RequestParam(required = false) Boolean favorite) {
        if (favorite == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Optional<Book> optionalBook = bookService.getBookById(id);
        if (!optionalBook.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Book book = optionalBook.get();
        book.setFavorite(favorite);

        Book updatedBook = bookService.editBook(book);
        return ResponseEntity.ok(updatedBook);
    }

    @PatchMapping("/cover/{id}")
    public ResponseEntity<Book> updateCover(@PathVariable("id") Long id, @RequestParam(required = false) String coverImage) {
        if (coverImage == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Optional<Book> optionalBook = bookService.getBookById(id);
        if (!optionalBook.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Book book = optionalBook.get();
        book.setCoverImage(coverImage);

        Book updatedBook = bookService.editBook(book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        return bookService.removeBook(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
