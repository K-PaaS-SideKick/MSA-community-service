package com.example.kpaas.repository;

import com.example.kpaas.model.PostLike;
import com.example.kpaas.model.PostLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostLikeRepository extends JpaRepository<PostLike, PostLikeId> {

    // 특정 게시물에 대해 사용자가 좋아요를 눌렀는지 확인
    @Query("SELECT pl FROM PostLike pl WHERE pl.post.postId = :postId AND pl.uid = :uid")
    PostLike findByPostIdAndUid(@Param("postId") Long postId, @Param("uid") Long uid);

    // 특정 게시물의 좋아요 수 조회
    @Query("SELECT COUNT(pl) FROM PostLike pl WHERE pl.post.postId = :postId")
    int countByPostId(@Param("postId") Long postId);
}
