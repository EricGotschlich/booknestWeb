package de.htwberlin.webtech.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;

    @ElementCollection
    private List<Integer> chapters = new ArrayList<>();

    private String summary;
    private String genre;
    private String readingProgress;

    public Book(Long id, String title, String author, String summary, String genre, String readingProgress) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.genre = genre;
        this.readingProgress = readingProgress;
    }

    public Book() { }

     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public String getAuthor() {
        return author;
    }

    public void setChapters(List<Integer> chapters) {
        this.chapters = chapters;
    }
    public List<Integer> getChapters() {
        return chapters;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getSummary() {
        return summary;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getGenre() {
        return genre;
    }

    public void addChapter(int chapterNumber) {
        chapters.add(chapterNumber);
    }

    public void setReadingProgress(String readingProgress) {
        this.readingProgress = readingProgress;
    }
    public String getReadingProgress() {
        return readingProgress;
    }
}
