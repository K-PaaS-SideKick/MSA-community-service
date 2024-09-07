package com.example.kpaas.repository;

import com.example.kpaas.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 게시물 검색 by 카테고리 및 키워드
    @Query("SELECT p FROM Post p WHERE EXISTS (SELECT c FROM p.categories c WHERE c.categoryName IN :categories) AND (LOWER(p.title) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(p.content) LIKE LOWER(CONCAT('%', :query, '%')))")
    List<Post> searchPosts(@Param("categories") List<String> categories, @Param("query") String query);

    // 특정 게시물의 좋아요 수 조회
    @Query("SELECT COUNT(pl) FROM PostLike pl WHERE pl.post.postId = :postId")
    int findPostLikeCount(@Param("postId") Long postId);

    // 게시물에 포함된 이미지 목록 조회
    @Query("SELECT pi.filePath, pi.fileType FROM PostImage pi WHERE pi.post.postId = :postId")
    List<Object[]> findPostImages(@Param("postId") Long postId);

    // 특정 게시물의 댓글 수 조회
    @Query("SELECT p.postCommentCount FROM Post p WHERE p.postId = :postId")
    int findPostCommentCount(@Param("postId") Long postId);

    // 게시물 스크랩 수 조회
    @Query("SELECT COUNT(ps) FROM PostScrap ps WHERE ps.post.postId = :postId")
    int findPostScrapCount(@Param("postId") Long postId);
}
