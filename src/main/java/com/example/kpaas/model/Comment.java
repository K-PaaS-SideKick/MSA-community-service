package com.example.kpaas.model;


import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private Long uid;
    private String nickname;
    private String content;
    @ManyToOne
    private Comment parentComment;

    // Getters and setters
}