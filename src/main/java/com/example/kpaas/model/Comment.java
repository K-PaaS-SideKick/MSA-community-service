package com.example.kpaas.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class Comment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long commentId;
//
//    private Long uid;
//    private String content;
//    private int likes;
//    private Long postId;
//
//    @ManyToOne
//    private Comment parentComment;
//
//    @Column(nullable = false, updatable = false)
//    private LocalDateTime createdAt;
//
//    @PrePersist
//    protected void onCreate() {
//        createdAt = LocalDateTime.now();
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private Long uid;

    private String content;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "comment_like_count")
    private int commentLikeCount;

    // 게시물과의 관계 설정
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    // 부모 댓글과의 관계 설정
    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    // 대댓글과의 관계 설정
    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private List<Comment> childComments;

    // 좋아요와의 관계 설정
    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<CommentLike> likes;

    // 생성 시간 자동 설정
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Getters and setters

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }


    public Long getuid() {
        return uid;
    }

    public void setuid(Long uid) {
        this.uid = uid;
    }

    public Post getPost_in_Comment() {
        return post;
    }

    public void setPost_in_Commnent(Post post) {
        this.post = post;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return commentLikeCount;
    }

    public void setLikes(int likes) {
        this.commentLikeCount = likes;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<Comment> getChildComments() {
        return childComments;
    }

    public void setChildComments(List<Comment> childComments) {
        this.childComments = childComments;
    }
}
