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
}
