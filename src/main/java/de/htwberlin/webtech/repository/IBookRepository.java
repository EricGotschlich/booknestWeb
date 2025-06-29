package de.htwberlin.webtech.repository;

import de.htwberlin.webtech.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends CrudRepository<Book, Long> {
    
    Iterable<Book> findByGenreContainingIgnoreCase(String genre);
    Iterable<Book> findByTitleContainingIgnoreCase(String title);
    Iterable<Book> findByAuthorContainingIgnoreCase(String author);
    Iterable<Book> findByFavoriteTrue();
}
