package com.example.kpaas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;
    @Column(name = "user_id", nullable = false)
    private String uid;
    @Column(length = 255) // length를 설정하여 VARCHAR로 제한
    private String title;
    @Lob
    @Column(nullable = false, length = 1000)
    private String content;
    private int view;
    private int upvote;
    private int scraps;
    @Column(name = "comment_count")
    private int comment_count;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;



    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostCategory> category;
    
    // Comment와의 OneToMany 관계
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
    
    // Postupvote와의 OneToMany 관계
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostUpvote> upvotes;

    // PostImage와의 OneToMany 관계
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostImage> images;

    // PostCategory와의 OneToMany 관계
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostCategory> postCategories;

    // PostsScrap와의 OneToMany 관계
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostsScrap> scrapsList;
    

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
