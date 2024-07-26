package com.example.kpaas.repository;

import com.example.kpaas.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.category IN :categories AND (p.title LIKE %:query% OR p.content LIKE %:query%)")
    List<Post> searchPosts(@Param("categories") List<String> categories, @Param("query") String query);
}
