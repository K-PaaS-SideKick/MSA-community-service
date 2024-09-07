package com.example.kpaas.repository;

import com.example.kpaas.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 특정 게시물에 속한 댓글 조회
    @Query("SELECT c FROM Comment c WHERE c.post.postId = :postId")
    List<Comment> findCommentsByPostId(@Param("postId") Long postId);

    // 부모 댓글에 속한 대댓글 조회
    @Query("SELECT c FROM Comment c WHERE c.parentComment.commentId = :parentCommentId")
    List<Comment> findChildComments(@Param("parentCommentId") Long parentCommentId);

    // 특정 사용자가 작성한 댓글 조회
    @Query("SELECT c FROM Comment c WHERE c.uid = :uid")
    List<Comment> findCommentsByUser(@Param("uid") Long uid);

    // 특정 댓글의 좋아요 수 조회
    @Query("SELECT COUNT(cl) FROM CommentLike cl WHERE cl.comment.commentId = :commentId")
    int findCommentLikeCount(@Param("commentId") Long commentId);
}
