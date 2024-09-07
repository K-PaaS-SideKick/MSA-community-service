package com.example.kpaas.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class CommentResponse {

    private Long commentId;          // 댓글 ID
    private Long postId;             // 게시물 ID
    private Long parentComment;      // 부모 댓글 ID (답글일 경우)
    private String content;          // 댓글 내용
    private int likes;               // 좋아요 수
    private LocalDateTime createdAt; // 생성 일시
    private List<CommentResponse> childComments;  // 답글 리스트

    public CommentResponse(Long commentId, String content, LocalDateTime createdAt) {
    }

    public CommentResponse() {

    }

    // Getters and Setters
    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getParentComment() {
        return parentComment;
    }

    public void setParentComment(Long parentComment) {
        this.parentComment = parentComment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<CommentResponse> getChildComments() { return childComments; }

    public void setChildComments(List<CommentResponse> childComments) { this.childComments = childComments; }


}
