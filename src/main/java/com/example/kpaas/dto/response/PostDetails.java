package com.example.kpaas.dto.response;

import java.time.LocalDate;
import java.util.List;

public class PostDetails {
    private Long postId;
    private String title;
    private byte[] image;
    private String content;
    private int view;
    private int like;
    private int scraps;
    private int comments;
    private LocalDate date;
    private List<CommentResponse> commentList;

    // Getters and setters
}