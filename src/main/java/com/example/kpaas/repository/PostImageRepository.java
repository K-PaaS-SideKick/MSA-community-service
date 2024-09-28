package com.example.kpaas.repository;

import com.example.kpaas.model.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
    // 특정 게시물의 이미지 리스트 조회
    List<PostImage> findByPostId(Long postId);
}
