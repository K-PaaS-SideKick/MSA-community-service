package com.example.kpaas.repository;

import com.example.kpaas.model.Commentupvote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentupvoteRepository extends JpaRepository<Commentupvote, Long> {
    // 댓글과 사용자 ID로 좋아요 여부 확인
    boolean existsByCommentIdAndUserId(Long commentId, Long userId);

    // 댓글의 좋아요 수 카운트
    long countByCommentId(Long commentId);
}
