package com.example.kpaas.repository;

import com.example.kpaas.model.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {

    // 특정 게시물의 이미지 목록 조회
    @Query("SELECT pi.filePath, pi.fileType FROM PostImage pi WHERE pi.post.postId = :postId")
    List<Object[]> findImagesByPostId(@Param("postId") Long postId);
}
