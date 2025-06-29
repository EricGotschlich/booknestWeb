package de.htwberlin.webtech.service;

import de.htwberlin.webtech.entity.Book;
import de.htwberlin.webtech.repository.IBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService service;

    @MockBean
    private IBookRepository repository;

    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    void setUpMockRepository() {
      
        book1 = new Book(1L, "Der Herr der Ringe", "J.R.R. Tolkien", 
                Arrays.asList(1, 2, 3), "Fantasy-Epos", "Fantasy", "50%", true, 
                "https://example.com/lotr.jpg");
                
        book2 = new Book(2L, "1984", "George Orwell", 
                Arrays.asList(1, 2), "Dystopischer Roman", "Dystopie", "75%", false, 
                "https://example.com/1984.jpg");
                
        book3 = new Book(3L, "Harry Potter", "J.K. Rowling", 
                Arrays.asList(1, 2, 3, 4), "Zauberer-Geschichte", "Fantasy", "100%", true, 
                "https://example.com/hp.jpg");

       
        doReturn(List.of(book1, book2, book3)).when(repository).findAll();
        doReturn(List.of(book1, book3)).when(repository).findByGenreContainingIgnoreCase("Fantasy");
        doReturn(List.of(book1, book3)).when(repository).findByFavoriteTrue();
        doReturn(List.of(book1)).when(repository).findByTitleContainingIgnoreCase("Herr");
        doReturn(Optional.of(book1)).when(repository).findById(1L);
        doReturn(Optional.empty()).when(repository).findById(999L);
        doReturn(true).when(repository).existsById(1L);
        doReturn(false).when(repository).existsById(999L);
        doReturn(book1).when(repository).save(any(Book.class));
    }

    @Test
    void testGetAllBooks() {
       
        List<Book> books = (List<Book>) service.getAllBooks();

        
        assertEquals(3, books.size(), "Anzahl aller Bücher sollte 3 sein");
        assertEquals("Der Herr der Ringe", books.get(0).getTitle());
        assertEquals("1984", books.get(1).getTitle());
        assertEquals("Harry Potter", books.get(2).getTitle());
        
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetBooksByGenre() {
        
        List<Book> fantasyBooks = (List<Book>) service.getBooksByGenre("Fantasy");

      
        assertEquals(2, fantasyBooks.size(), "Anzahl Fantasy-Bücher sollte 2 sein");
        assertEquals("Der Herr der Ringe", fantasyBooks.get(0).getTitle());
        assertEquals("Harry Potter", fantasyBooks.get(1).getTitle());
        
        verify(repository, times(1)).findByGenreContainingIgnoreCase("Fantasy");
    }

    @Test
    void testGetFavoriteBooks() {
       
        List<Book> favoriteBooks = (List<Book>) service.getFavoriteBooks();

        
        assertEquals(2, favoriteBooks.size(), "Anzahl Lieblingsbücher sollte 2 sein");
        assertTrue(favoriteBooks.get(0).getFavorite());
        assertTrue(favoriteBooks.get(1).getFavorite());
        
        verify(repository, times(1)).findByFavoriteTrue();
    }

    @Test
    void testGetBooksByTitle() {
        
        List<Book> books = (List<Book>) service.getBooksByTitle("Herr");

       
        assertEquals(1, books.size(), "Anzahl gefundener Bücher sollte 1 sein");
        assertEquals("Der Herr der Ringe", books.get(0).getTitle());
        
        verify(repository, times(1)).findByTitleContainingIgnoreCase("Herr");
    }

    @Test
    void testGetBookById_ExistingBook() {
        
        Optional<Book> bookOptional = service.getBookById(1L);

       
        assertTrue(bookOptional.isPresent(), "Buch sollte gefunden werden");
        assertEquals("Der Herr der Ringe", bookOptional.get().getTitle());
        assertEquals(1L, bookOptional.get().getId());
        
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testGetBookById_NonExistingBook() {
        
        Optional<Book> bookOptional = service.getBookById(999L);

      
        assertFalse(bookOptional.isPresent(), "Buch sollte nicht gefunden werden");
        
        verify(repository, times(1)).findById(999L);
    }

    @Test
    void testAddBook() {
        
        Book newBook = new Book();
        newBook.setTitle("Neues Buch");
        newBook.setAuthor("Neuer Autor");

        
        Book savedBook = service.addBook(newBook);

        
        assertNotNull(savedBook);
        assertEquals("Der Herr der Ringe", savedBook.getTitle()); 

        verify(repository, times(1)).save(newBook);
    }

    @Test
    void testEditBook() {
        
        Book updatedBook = new Book();
        updatedBook.setId(1L);
        updatedBook.setTitle("Aktualisierter Titel");

        
        Book result = service.editBook(updatedBook);

        
        assertNotNull(result);
        
        verify(repository, times(1)).save(updatedBook);
    }

    @Test
    void testRemoveBook_ExistingBook() {
       
        boolean result = service.removeBook(1L);

        
        assertTrue(result, "Löschen sollte erfolgreich sein");
        
        verify(repository, times(1)).existsById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testRemoveBook_NonExistingBook() {
       
        boolean result = service.removeBook(999L);

      
        assertFalse(result, "Löschen sollte fehlschlagen für nicht existierendes Buch");
        
        verify(repository, times(1)).existsById(999L);
        verify(repository, never()).deleteById(999L);
    }

    @Test
    void testDeleteBook() {
        
        service.deleteBook(1L);

        
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testGetBooksByGenre_CaseInsensitive() {
        
        service.getBooksByGenre("fantasy");

        
        verify(repository, times(1)).findByGenreContainingIgnoreCase("fantasy");
    }

    @Test
    void testGetBooksByTitle_PartialMatch() {
        
        service.getBooksByTitle("potter");

       
        verify(repository, times(1)).findByTitleContainingIgnoreCase("potter");
    }

    @Test
    void testRepositoryIsCalledCorrectly() {
      
        
      
        service.getAllBooks();
        verify(repository).findAll();
        
       
        service.getBooksByGenre("Test");
        verify(repository).findByGenreContainingIgnoreCase("Test");
        
      
        service.getFavoriteBooks();
        verify(repository).findByFavoriteTrue();
        
       
        service.getBookById(1L);
        verify(repository).findById(1L);
        
        
        service.removeBook(1L);
        verify(repository).existsById(1L);
    }
}