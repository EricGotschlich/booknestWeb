package de.htwberlin.webtech.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String author;

    @ElementCollection
    private List<Integer> chapters = new ArrayList<>();

    @Column
    private String summary;

    @Column
    private String genre;

    @Column
    private String readingProgress;
    
    @Column
    private Boolean favorite = false;
    
    @Column(length = 2000)
    private String coverImage;
}
