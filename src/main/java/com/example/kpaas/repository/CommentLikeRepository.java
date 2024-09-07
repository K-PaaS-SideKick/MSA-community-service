package com.example.kpaas.repository;

import com.example.kpaas.model.CommentLike;
import com.example.kpaas.model.CommentLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentLikeRepository extends JpaRepository<CommentLike, CommentLikeId> {

    // 특정 댓글에 대한 좋아요 조회
    @Query("SELECT cl FROM CommentLike cl WHERE cl.comment.commentId = :commentId AND cl.uid = :uid")
    CommentLike findByCommentIdAndUid(@Param("commentId") Long commentId, @Param("uid") Long uid);

    // 특정 댓글의 좋아요 수 조회
    @Query("SELECT COUNT(cl) FROM CommentLike cl WHERE cl.comment.commentId = :commentId")
    int countByCommentId(@Param("commentId") Long commentId);
}
