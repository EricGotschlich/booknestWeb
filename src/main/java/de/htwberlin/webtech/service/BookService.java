package de.htwberlin.webtech.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import de.htwberlin.webtech.repository.IBookRepository;
import de.htwberlin.webtech.entity.Book; 

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
    public void deleteBook(Integer id) {
        this.ibookRepository.deleteById(id);
    }
}