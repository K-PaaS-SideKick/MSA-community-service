package com.example.kpaas.dto.request;

public class LikeRequest {

    private Long postId;  // 게시물 ID (게시물에 좋아요를 클릭할 때)
    private Long commentId; // 댓글 ID (댓글에 좋아요를 클릭할 때)

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
}
