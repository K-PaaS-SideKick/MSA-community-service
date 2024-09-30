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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrap_id")
    private Long scrap_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", insertable = false, nullable = false)
    private Post post;


    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

}
