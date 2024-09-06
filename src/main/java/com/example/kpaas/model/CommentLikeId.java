package com.example.kpaas.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CommentLikeId implements Serializable {

    private Long commentId;
    private Long uid;

    // 기본 생성자
    public CommentLikeId() {}

    public CommentLikeId(Long commentId, Long uid) {
        this.commentId = commentId;
        this.uid = uid;
    }

    // Getters and Setters
    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    // equals()와 hashCode() 구현 (복합키 비교를 위한 메서드들)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentLikeId that = (CommentLikeId) o;
        return Objects.equals(commentId, that.commentId) && Objects.equals(uid, that.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, uid);
    }
}
