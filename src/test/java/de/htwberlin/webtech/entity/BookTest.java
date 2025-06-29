package de.htwberlin.webtech.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

@SpringBootTest
public class BookTest {

    @Test
    void testBookToString() {
       
        final Book book = new Book();
        book.setId(1L);
        book.setTitle("Der Herr der Ringe");
        book.setAuthor("J.R.R. Tolkien");
        book.setGenre("Fantasy");
        book.setSummary("Ein episches Fantasy-Abenteuer in Mittelerde");
        book.setReadingProgress("75%");
        book.setChapters(Arrays.asList(1, 2, 3, 4, 5));
        book.setFavorite(true);
        book.setCoverImage("https://example.com/lotr-cover.jpg");

       
        final String result = book.toString();

        
        final String expected = "Book(id=1, " +
                "title=Der Herr der Ringe, " +
                "author=J.R.R. Tolkien, " +
                "chapters=[1, 2, 3, 4, 5], " +
                "summary=Ein episches Fantasy-Abenteuer in Mittelerde, " +
                "genre=Fantasy, " +
                "readingProgress=75%, " +
                "favorite=true, " +
                "coverImage=https://example.com/lotr-cover.jpg)";
                
        assertEquals(expected, result);
    }

    @Test
    void testBookConstructorAndGetters() {
       
        final Book book = new Book();
        
      
        book.setId(2L);
        book.setTitle("1984");
        book.setAuthor("George Orwell");
        book.setGenre("Dystopie");
        book.setSummary("Eine düstere Vision der Zukunft");
        book.setReadingProgress("50%");
        book.setFavorite(false);
        book.setCoverImage("https://example.com/1984-cover.jpg");

       
        assertEquals(2L, book.getId());
        assertEquals("1984", book.getTitle());
        assertEquals("George Orwell", book.getAuthor());
        assertEquals("Dystopie", book.getGenre());
        assertEquals("Eine düstere Vision der Zukunft", book.getSummary());
        assertEquals("50%", book.getReadingProgress());
        assertFalse(book.getFavorite());
        assertEquals("https://example.com/1984-cover.jpg", book.getCoverImage());
    }

    @Test
    void testBookWithAllArgsConstructor() {
     
        final Book book = new Book(
            3L,
            "Harry Potter",
            "J.K. Rowling",
            Arrays.asList(1, 2, 3, 4, 5, 6, 7),
            "Ein junger Zauberer entdeckt seine magischen Kräfte",
            "Fantasy",
            "Kapitel 4",
            true,
            "https://example.com/hp-cover.jpg"
        );

        
        assertEquals(3L, book.getId());
        assertEquals("Harry Potter", book.getTitle());
        assertEquals("J.K. Rowling", book.getAuthor());
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7), book.getChapters());
        assertEquals("Ein junger Zauberer entdeckt seine magischen Kräfte", book.getSummary());
        assertEquals("Fantasy", book.getGenre());
        assertEquals("Kapitel 4", book.getReadingProgress());
        assertTrue(book.getFavorite());
        assertEquals("https://example.com/hp-cover.jpg", book.getCoverImage());
    }

    @Test
    void testBookDefaultValues() {
        
        final Book book = new Book();

       
        assertNull(book.getId());
        assertNull(book.getTitle());
        assertNull(book.getAuthor());
        assertNotNull(book.getChapters());
        assertTrue(book.getChapters().isEmpty());
        assertNull(book.getSummary());
        assertNull(book.getGenre());
        assertNull(book.getReadingProgress());
        assertEquals(false, book.getFavorite());
        assertNull(book.getCoverImage());
    }

    @Test
    void testBookSetters() {
       
        final Book book = new Book();

      
        book.setId(4L);
        book.setTitle("Die Tribute von Panem");
        book.setAuthor("Suzanne Collins");
        book.setGenre("Dystopie");
        book.setSummary("Katniss kämpft in einer brutalen Arena");
        book.setReadingProgress("25%");
        book.setChapters(Arrays.asList(1, 2, 3));
        book.setFavorite(true);
        book.setCoverImage("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==");

       
        assertEquals(4L, book.getId());
        assertEquals("Die Tribute von Panem", book.getTitle());
        assertEquals("Suzanne Collins", book.getAuthor());
        assertEquals("Dystopie", book.getGenre());
        assertEquals("Katniss kämpft in einer brutalen Arena", book.getSummary());
        assertEquals("25%", book.getReadingProgress());
        assertEquals(Arrays.asList(1, 2, 3), book.getChapters());
        assertTrue(book.getFavorite());
        assertTrue(book.getCoverImage().startsWith("data:image/png;base64,"));
    }

    @Test
    void testBookEqualsAndHashCode() {
       
        final Book book1 = new Book();
        book1.setId(5L);
        book1.setTitle("Test Buch");
        book1.setAuthor("Test Autor");

        final Book book2 = new Book();
        book2.setId(5L);
        book2.setTitle("Test Buch");
        book2.setAuthor("Test Autor");

        final Book book3 = new Book();
        book3.setId(6L);
        book3.setTitle("Anderes Buch");
        book3.setAuthor("Anderer Autor");

       
        assertEquals(book1, book2);
        assertNotEquals(book1, book3);
        assertEquals(book1.hashCode(), book2.hashCode());
    }

    @Test
    void testBookChaptersModification() {
        
        final Book book = new Book();
        
        
        book.getChapters().add(1);
        book.getChapters().add(2);
        book.getChapters().add(3);

       
        assertEquals(3, book.getChapters().size());
        assertTrue(book.getChapters().contains(1));
        assertTrue(book.getChapters().contains(2));
        assertTrue(book.getChapters().contains(3));
    }

    @Test
    void testBookFavoriteToggle() {
        
        final Book book = new Book();
        
        
        assertEquals(false, book.getFavorite());
        
        book.setFavorite(true);
        assertTrue(book.getFavorite());
        
        book.setFavorite(false);
        assertFalse(book.getFavorite());
    }

    @Test
    void testBookCoverImageHandling() {
        
        final Book book = new Book();
        
       
        book.setCoverImage("https://example.com/cover.jpg");
        assertEquals("https://example.com/cover.jpg", book.getCoverImage());
        
        
        final String base64Image = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==";
        book.setCoverImage(base64Image);
        assertEquals(base64Image, book.getCoverImage());
        
        
        book.setCoverImage(null);
        assertNull(book.getCoverImage());
    }
}