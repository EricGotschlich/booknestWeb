package de.htwberlin.webtech.repository;


import de.htwberlin.webtech.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface IBookRepository extends CrudRepository<Book, Integer>  {

}
