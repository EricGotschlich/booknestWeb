package de.htwberlin.webtech.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private String title;
    private String author;

    @ElementCollection
    private List<Integer> chapters = new ArrayList<>();

    private String summary;
    private String genre;
    private String readingProgress;



}
