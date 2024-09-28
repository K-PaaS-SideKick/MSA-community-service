package com.example.kpaas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "post_upvotes")
@Getter
@Setter
public class PostUpvote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;


//    private Long postId;

    @Column(name = "user_id", nullable = false)
    private String userId;


}
