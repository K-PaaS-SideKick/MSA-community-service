package com.example.kpaas.repository;

import com.example.kpaas.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    // Add custom query methods if needed
}