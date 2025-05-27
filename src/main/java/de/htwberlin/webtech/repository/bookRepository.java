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
        Book book1 = new Book(
                1L,
                "The Alchemist",
                "Paulo Coelho",
                "Santiago, an Andalusian shepherd boy, embarks on a journey to the Egyptian pyramids after having a recurring dream of finding treasure. Along the way, he learns about the Soul of the World and the importance of following one's dreams.",
                "Fiction",
                "Finished"
        );
        book1.addChapter(1);
        book1.addChapter(2);
        book1.addChapter(3);
        books.add(book1);

        Book book2 = new Book(
                2L,
                "1984",
                "George Orwell",
                "In a dystopian future, Winston Smith lives under the oppressive regime of the Party led by Big Brother. As he begins to question the system, he secretly rebels through thought, love, and truth in a world where even thinking against the regime is forbidden.",
                "Dystopian",
                "Chapter 5"
        );
        book2.addChapter(1);
        book2.addChapter(2);
        book2.addChapter(3);
        book2.addChapter(4);
        books.add(book2);

        Book book3 = new Book(
                3L,
                "The Metamorphosis",
                "Franz Kafka",
                "Gregor Samsa wakes up one morning to find himself transformed into a monstrous insect. Isolated and rejected by his family, his physical and emotional deterioration unfolds in a surreal exploration of alienation and guilt.",
                "Classic",
                "Chapter 2"
        );
        book3.addChapter(1);
        book3.addChapter(2);
        books.add(book3);

        // Franz Kafka
        Book book4 = new Book(
                4L,
                "The Trial",
                "Franz Kafka",
                "Josef K., a respected bank officer, is suddenly arrested without being told the charges. As he navigates an absurd and opaque legal system, the novel delves into themes of bureaucracy, guilt, and existential dread.",
                "Absurdist Fiction",
                "Chapter 3"
        );
        book4.addChapter(1);
        book4.addChapter(2);
        book4.addChapter(3);
        books.add(book4);

        Book book5 = new Book(
                5L,
                "The Castle",
                "Franz Kafka",
                "A land surveyor known only as K. arrives in a village governed by a mysterious bureaucracy operating from a nearby castle. His attempts to access the officials are constantly thwarted, exploring themes of alienation and powerlessness.",
                "Philosophical Fiction",
                "Chapter 4"
        );
        book5.addChapter(1);
        book5.addChapter(2);
        books.add(book5);

        // George Orwell
        Book book6 = new Book(
                6L,
                "Animal Farm",
                "George Orwell",
                "A group of farm animals rebel against their human farmer, hoping to create a society where all animals are equal. Over time, the pigs take control, and the revolution turns into a dictatorship as oppressive as the one it replaced.",
                "Political Satire",
                "Chapter 7"
        );
        book6.addChapter(1);
        book6.addChapter(2);
        book6.addChapter(3);
        books.add(book6);

        Book book7 = new Book(
                7L,
                "Homage to Catalonia",
                "George Orwell",
                "In this personal memoir, Orwell recounts his experiences fighting in the Spanish Civil War, offering firsthand insight into the realities of war, ideological conflicts, and betrayal among the anti-fascist forces.",
                "Non-fiction",
                "Chapter 5"
        );
        book7.addChapter(1);
        book7.addChapter(2);
        book7.addChapter(3);
        book7.addChapter(4);
        books.add(book7);

        // Fyodor Dostoevsky
        Book book8 = new Book(
                8L,
                "Crime and Punishment",
                "Fyodor Dostoevsky",
                "Raskolnikov, a destitute student, murders a pawnbroker in an attempt to assert his theory that some people have the right to kill. The novel explores guilt, redemption, and the psychological torment of conscience.",
                "Psychological Fiction",
                "Chapter 6"
        );
        book8.addChapter(1);
        book8.addChapter(2);
        book8.addChapter(3);
        books.add(book8);

        Book book9 = new Book(
                9L,
                "The Idiot",
                "Fyodor Dostoevsky",
                "Prince Myshkin, a kind and compassionate man, returns to Russian society and quickly becomes entangled in its corrupt and chaotic nature. His honesty and innocence clash with the cruelty of those around him.",
                "Philosophical Novel",
                "Chapter 8"
        );
        book9.addChapter(1);
        book9.addChapter(2);
        books.add(book9);

        Book book10 = new Book(
                10L,
                "The Brothers Karamazov",
                "Fyodor Dostoevsky",
                "Three brothers—each representing different facets of human nature—struggle with morality, faith, and family loyalty in the wake of their father’s murder. A profound philosophical and theological investigation.",
                "Theological Novel",
                "Chapter 9"
        );
        book10.addChapter(1);
        book10.addChapter(2);
        book10.addChapter(3);
        books.add(book10);
    }
}
