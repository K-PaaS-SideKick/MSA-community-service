package com.example.kpaas.repository;

import com.example.kpaas.model.PostsScrap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostScrapRepository extends JpaRepository<PostsScrap, Long> {
    // 게시물과 사용자 ID로 스크랩 여부 확인
    boolean existsByPostIdAndUserId(Long postId, Long userId);

    // 특정 게시물의 스크랩 수
    long countByPostId(Long postId);
}
