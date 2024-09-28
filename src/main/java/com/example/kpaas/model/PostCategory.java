package com.example.kpaas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "post_category")
@Getter
@Setter
public class PostCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Category_id;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "category", nullable = false)
    private String category;

}
