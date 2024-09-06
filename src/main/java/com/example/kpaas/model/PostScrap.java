package com.example.kpaas.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Posts_Scraps")
public class PostScrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scrapId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private Long uid;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Getter 및 Setter 메서드들
    public Long getScraps() {
        return scrapId;
    }

    public void setScraps(Long scraps) {
        this.scrapId = scraps;
    }
}
