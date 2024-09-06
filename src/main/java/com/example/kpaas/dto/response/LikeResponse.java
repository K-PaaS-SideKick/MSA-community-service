package com.example.kpaas.dto.response;

public class LikeResponse {

    private Long postId; // 게시물 ID (게시물에 좋아요를 클릭한 경우)
    private Long commentId; // 댓글 ID (댓글에 좋아요를 클릭한 경우)
    private int likes; // 현재 좋아요 수

    // Constructor
    public LikeResponse(Long postId, int likes) {
        this.postId = postId;
        this.commentId = commentId;
        this.likes = likes;
    }

    // Getters and Setters
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

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
