package com.example.kpaas.dto.response;

public class CommentLikeResponse {
    private Long commentId;
    private int likes;

    // Default constructor
    public CommentLikeResponse() {
    }

    // Constructor with parameters
    public CommentLikeResponse(Long commentId, int likes) {
        this.commentId = commentId;
        this.likes = likes;
    }

    // Getters and setters
    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
