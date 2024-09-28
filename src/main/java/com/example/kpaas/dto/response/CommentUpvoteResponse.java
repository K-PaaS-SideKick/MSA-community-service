package com.example.kpaas.dto.response;

public class CommentUpvoteResponse {
    private Long commentId;
    private int upvotes;

    // Default constructor
    public CommentUpvoteResponse() {
    }

    // Constructor with parameters
    public CommentUpvoteResponse(Long commentId, int upvotes) {
        this.commentId = commentId;
        this.upvotes = upvotes;
    }

    // Getters and setters
    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }
}
