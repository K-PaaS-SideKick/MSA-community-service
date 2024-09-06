package com.example.kpaas.dto.request;

public class CommentRequest {

    private Long commentId;  // 댓글 ID (좋아요, 삭제, 업데이트에 사용)
    private Long postId;     // 댓글이 달릴 게시물 ID
    private Long parentComment; // 부모 댓글 ID (답글일 경우)
    private String content;  // 댓글 내용

    // Getters and Setters
    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getPostId_Comment() {
        return postId;
    }

    public void setPostId_Comment(Long postId) {
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
}
