package com.example.kpaas.repository;

import com.example.kpaas.model.PostUpvote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostUpvoteRepository extends JpaRepository<PostUpvote, Long> {
    // 게시물과 사용자 ID로 좋아요 여부 확인
    boolean existsByPostIdAndUserId(Long postId, Long userId);

    // 게시물의 좋아요 수 카운트
    long countByPostId(Long postId);
}
