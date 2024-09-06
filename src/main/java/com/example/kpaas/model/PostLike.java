package com.example.kpaas.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Post_Likes")
public class PostLike {

    @EmbeddedId
    private PostLikeId id;

    @ManyToOne
    @MapsId("postId")
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "uid", nullable = false)
    private Long uid;

    // 기본 생성자
    public PostLike() {}

    public PostLike(Post post, Long uid) {
        this.id = new PostLikeId(post.getPostId(), uid);
        this.post = post;
        this.uid = uid;
    }

    // Getters and Setters
    public PostLikeId getId() {
        return id;
    }

    public void setId(PostLikeId id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    // equals()와 hashCode() 메서드를 PostLikeId에서 위임받아 사용
}

