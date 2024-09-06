package com.example.kpaas.repository;

import com.example.kpaas.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByParentCommentIsNull();

    List<Comment> findByParentCommentUid(Long parentCommentId);

    List<Comment> findByPostId(Long postId);

/*
    @Query("SELECT c FROM Comment c WHERE c.postId = :postId")
    List<Comment> findByPostId(@Param("postId") Long postId);
*/

    /*
    // 최상위 댓글 조회
    List<Comment> findByParentCommentIsNull();

    // 특정 부모 댓글의 대댓글 조회
    List<Comment> findByParentCommentUid(Long parentCommentId);

    // 특정 게시물의 댓글 조회
    List<Comment> findByPostId(Long postId);

    // 특정 게시물의 댓글 수 조회
    @Query("SELECT COUNT(c) FROM Comment c WHERE c.postId = :postId")
    int countCommentsByPostId(@Param("postId") Long postId);

    // 특정 댓글의 좋아요 수 조회
    @Query("SELECT c.commentLikeCount FROM Comment c WHERE c.id = :commentId")
    int findCommentLikeCountById(@Param("commentId") Long commentId);
    */
}
