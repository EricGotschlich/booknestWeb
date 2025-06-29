package de.htwberlin.webtech.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import de.htwberlin.webtech.repository.IBookRepository;
import de.htwberlin.webtech.entity.Book; 
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private IBookRepository ibookRepository;

    public BookService(IBookRepository ibookRepository) {
        this.ibookRepository = ibookRepository;
    }

    public Iterable<Book> getAllBooks() {
        return this.ibookRepository.findAll();
    }
    
    public Book addBook(final Book book) {
        return this.ibookRepository.save(book);
    }
    
    public void deleteBook(Long id) {
        this.ibookRepository.deleteById(id);
    }

    public Optional<Book> getBookById(Long id) {
        return this.ibookRepository.findById(id);
    }

    public Iterable<Book> getBooksByGenre(String genre) {
        return this.ibookRepository.findByGenreContainingIgnoreCase(genre);
    }

    public Iterable<Book> getBooksByTitle(String title) {
        return this.ibookRepository.findByTitleContainingIgnoreCase(title);
    }

    public Book editBook(Book book) {
        return this.ibookRepository.save(book);
    }

    public boolean removeBook(Long id) {
        if (this.ibookRepository.existsById(id)) {
            this.ibookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Iterable<Book> getFavoriteBooks() {
        return this.ibookRepository.findByFavoriteTrue();
    }
}