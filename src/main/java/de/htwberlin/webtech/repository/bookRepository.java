package de.htwberlin.webtech.repository;

import de.htwberlin.webtech.entity.Book;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class bookRepository {

    private final List<Book> books = new ArrayList<>();

    public List<Book> findAll() {
        return books;
    }

    @PostConstruct
    private void init() {
        Book theAlchemist = new Book(
                "The Alchemist",
                "Paulo Coelho",
                "An Andalusian shepherd boy named Santiago travels from Spain to the Egyptian pyramids in search of treasure. "
                        + "He learns to follow his dreams, listen to his heart, and read the omens.",
                "Fiction",
                "Finished"
        );

        // Beispielkapitel hinzufügen
        theAlchemist.addChapter(1);
        theAlchemist.addChapter(2);
        theAlchemist.addChapter(3);

        books.add(theAlchemist);
    }
}
