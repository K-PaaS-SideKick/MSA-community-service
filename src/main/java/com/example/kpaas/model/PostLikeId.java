package com.example.kpaas.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PostLikeId implements Serializable {

    private Long postId;
    private Long uid;

    // 기본 생성자
    public PostLikeId() {}

    public PostLikeId(Long postId, Long uid) {
        this.postId = postId;
        this.uid = uid;
    }

    // Getters and Setters
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
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
        PostLikeId that = (PostLikeId) o;
        return Objects.equals(postId, that.postId) && Objects.equals(uid, that.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, uid);
    }
}
