package com.example.kpaas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "post_scraps")
@Getter
@Setter
public class PostsScrap {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Scrap_id;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

}
