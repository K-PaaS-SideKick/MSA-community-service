package com.example.kpaas.repository;

import com.example.kpaas.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Add custom query methods if needed
}