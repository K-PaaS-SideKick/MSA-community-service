package com.example.kpaas.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Comment_Likes")
public class CommentLike {

    @EmbeddedId
    private CommentLikeId id;

    @ManyToOne
    @MapsId("commentId")  // 복합키의 commentId를 사용
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    @Column(name = "uid", nullable = false, insertable = false, updatable = false)
    private Long uid;

    // 기본 생성자
    public CommentLike() {}

    public CommentLike(Comment comment, Long uid) {
        this.id = new CommentLikeId(comment.getCommentId(), uid);
        this.comment = comment;
        this.uid = uid;
    }

    // Getter 및 Setter 메서드들
    public CommentLikeId getId() {
        return id;
    }

    public void setId(CommentLikeId id) {
        this.id = id;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    // equals()와 hashCode()는 CommentLikeId에서 처리됩니다
}

