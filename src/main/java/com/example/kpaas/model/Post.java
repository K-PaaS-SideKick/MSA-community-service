package com.example.kpaas.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Post {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long postId;
//    private String title;
//    private String content;
//    private int view;
//    private int like;
//    private int scraps;
//    private int comments;
//    private LocalDate date;
//
//    @ElementCollection
//    @CollectionTable(name = "post_category", joinColumns = @JoinColumn(name = "post_id"))
//    @Column(name = "category")
//    private List<String> category;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long postId;

    private Long uid;

    private String title;

    private String content;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @Column(name = "view_count")
    private int viewCount;

    @Column(name = "post_comment_count")
    private int postCommentCount;

    @Column(name = "post_like_count")
    private int postLikeCount;

    @Column(name = "post_scrap_count")
    private int postScrapCount;

    // 카테고리와의 관계 설정
    @ManyToMany
    @JoinTable(
            name = "Post_Categories",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    // 이미지와의 관계 설정
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostImage> images;

    // 좋아요와의 관계 설정
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostLike> likes;

    // 스크랩과의 관계 설정
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostScrap> scraps;

    // 댓글과의 관계 설정
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    // 생성 및 수정 시간 자동 설정
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getters and setters

    public Long getPostId() { return postId; }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getView() {
        return viewCount;
    }

    public void setView(int view) {
        this.viewCount = view;
    }

    public int getLike() {
        return postLikeCount;
    }

    public void setLike(int like) {
        this.postLikeCount = like;
    }

    public int getCommentsCount() {
        return postCommentCount;
    }

    public void setCommentsCount(int comments) {
        this.postCommentCount = comments;
    }

    public int getPostScrapCount() { return postScrapCount; }

    public void setPostScrapCount(int postScrapCount) { this.postScrapCount = postScrapCount; }

    public LocalDateTime getCreatedAtDate() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAtAtDate() {
        return updatedAt;
    }

    public List<Category> getCategories() { return categories; }

    public void setCategories(List<Category> categories) { this.categories = categories; }

    public void setDate(LocalDateTime date) {
        this.updatedAt = date;
    }


}
