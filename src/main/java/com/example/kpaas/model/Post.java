package com.example.kpaas.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String title;
    private String content;
    private int view;
    private int like;
    private int scraps;
    private int comments;
    private LocalDate date;
    @ElementCollection
    private List<String> category;

    // Getters and setters
}