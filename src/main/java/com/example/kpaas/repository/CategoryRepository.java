package com.example.kpaas.repository;

import com.example.kpaas.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // 카테고리 이름으로 카테고리 조회
    @Query("SELECT c FROM Category c WHERE LOWER(c.categoryName) = LOWER(:categoryName)")
    Category findByCategoryName(@Param("categoryName") String categoryName);

    // 특정 카테고리에 속한 게시물 조회
    @Query("SELECT c.posts FROM Category c WHERE c.categoryId = :categoryId")
    List<Object[]> findPostsByCategory(@Param("categoryId") Long categoryId);
}
