package de.htwberlin.webtech.controller;

import de.htwberlin.webtech.entity.Book;
import de.htwberlin.webtech.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService service;

    @BeforeEach
    void setUpMockRepository() {
        final Book lotr = new Book(1L, "Der Herr der Ringe", "J.R.R. Tolkien", 
                Arrays.asList(1, 2, 3), "Fantasy-Epos über Mittelerde", "Fantasy", 
                "50%", true, "https://example.com/lotr.jpg");
                
        final Book orwell = new Book(2L, "1984", "George Orwell", 
                Arrays.asList(1, 2), "Dystopischer Roman", "Dystopie", 
                "75%", false, "https://example.com/1984.jpg");

        
        when(service.getBookById(1L)).thenReturn(Optional.of(lotr));
        when(service.getBookById(999L)).thenReturn(Optional.empty());
        when(service.getAllBooks()).thenReturn(List.of(lotr, orwell));
        when(service.getBooksByGenre("Fantasy")).thenReturn(List.of(lotr));
        when(service.getBooksByTitle("Herr")).thenReturn(List.of(lotr));
        when(service.getFavoriteBooks()).thenReturn(List.of(lotr));
        when(service.addBook(any(Book.class))).thenReturn(lotr);
        when(service.editBook(any(Book.class))).thenReturn(lotr);
        when(service.removeBook(1L)).thenReturn(true);
        when(service.removeBook(999L)).thenReturn(false);
    }

    @Test
    void testGetBookById_NotFound() throws Exception {
        this.mockMvc.perform(get("/books/999"))
                .andExpect(status().isNotFound());
    }


    @Test
    void testUpdateBook() throws Exception {
        final String bookJson = "{\"title\":\"Aktualisiertes Buch\",\"author\":\"Aktualisierter Autor\",\"genre\":\"Fantasy\",\"summary\":\"Neue Summary\",\"readingProgress\":\"100%\",\"favorite\":true}";
        
        this.mockMvc.perform(put("/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
                
        verify(service, times(1)).editBook(any(Book.class));
    }

    @Test
    void testUpdateReadingProgress() throws Exception {
        this.mockMvc.perform(patch("/books/progress/1")
                .param("progress", "85%"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
                
        verify(service, times(1)).getBookById(1L);
        verify(service, times(1)).editBook(any(Book.class));
    }

    @Test
    void testUpdateReadingProgress_BadRequest() throws Exception {
        this.mockMvc.perform(patch("/books/progress/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateReadingProgress_NotFound() throws Exception {
        this.mockMvc.perform(patch("/books/progress/999")
                .param("progress", "85%"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateFavorite() throws Exception {
        this.mockMvc.perform(patch("/books/favorite/1")
                .param("favorite", "true"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
                
        verify(service, times(1)).getBookById(1L);
        verify(service, times(1)).editBook(any(Book.class));
    }

    @Test
    void testUpdateFavorite_BadRequest() throws Exception {
        this.mockMvc.perform(patch("/books/favorite/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateFavorite_NotFound() throws Exception {
        this.mockMvc.perform(patch("/books/favorite/999")
                .param("favorite", "true"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateCover() throws Exception {
        this.mockMvc.perform(patch("/books/cover/1")
                .param("coverImage", "https://example.com/new-cover.jpg"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
                
        verify(service, times(1)).getBookById(1L);
        verify(service, times(1)).editBook(any(Book.class));
    }

    @Test
    void testUpdateCover_BadRequest() throws Exception {
        this.mockMvc.perform(patch("/books/cover/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateCover_NotFound() throws Exception {
        this.mockMvc.perform(patch("/books/cover/999")
                .param("coverImage", "https://example.com/new-cover.jpg"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteBook_Success() throws Exception {
        this.mockMvc.perform(delete("/books/1"))
                .andExpect(status().isNoContent());
                
        verify(service, times(1)).removeBook(1L);
    }

    @Test
    void testDeleteBook_NotFound() throws Exception {
        this.mockMvc.perform(delete("/books/999"))
                .andExpect(status().isNotFound());
                
        verify(service, times(1)).removeBook(999L);
    }

    @Test
    void testGetBooksByTitle_EmptyResult() throws Exception {
        when(service.getBooksByTitle("Nicht vorhanden")).thenReturn(List.of());
        
        this.mockMvc.perform(get("/books/title/Nicht vorhanden"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @Test
    void testGetFavoriteBooks_EmptyResult() throws Exception {
        when(service.getFavoriteBooks()).thenReturn(List.of());
        
        this.mockMvc.perform(get("/books/favorites"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @Test
    void testServiceMethodsAreCalled() throws Exception {
        
        
        
        this.mockMvc.perform(get("/books"));
        verify(service).getAllBooks();
        
       
        this.mockMvc.perform(get("/books/genre/Fantasy"));
        verify(service).getBooksByGenre("Fantasy");
        
      
        this.mockMvc.perform(get("/books/title/Test"));
        verify(service).getBooksByTitle("Test");
        
      
        this.mockMvc.perform(get("/books/favorites"));
        verify(service).getFavoriteBooks();
        
      
        this.mockMvc.perform(get("/books/1"));
        verify(service).getBookById(1L);
    }
}