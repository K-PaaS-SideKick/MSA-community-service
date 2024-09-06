package com.example.kpaas.dto.response;

public class PostResponse {

    private Long postId; // 게시물 ID
    private String title; // 게시물 제목

    // Constructor
    public PostResponse(Long postId, String title) {
        this.postId = postId;
        this.title = title;
    }

    // Getters and Setters
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
