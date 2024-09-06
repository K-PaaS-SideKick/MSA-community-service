package com.example.kpaas.dto.response;

public class CommentLikeResponse {

    private Long commentId; // 댓글 ID
    private int likes;      // 현재 좋아요 수

    // Constructor
    public CommentLikeResponse(Long commentId, int likes) {
        this.commentId = commentId;
        this.likes = likes;
    }

    // Getters and Setters
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
