package com.example.kpaas.repository;

import com.example.kpaas.model.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostCategoryRepository extends JpaRepository<PostCategory, Long> {
    // 특정 게시물의 카테고리 리스트 조회
    List<PostCategory> findByPostId(Long postId);

    // 특정 카테고리의 게시물 리스트 조회
    List<PostCategory> findByCategory(String category);
}
