package com.example.kpaas.repository;

import com.example.kpaas.model.PostScrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostScrapRepository extends JpaRepository<PostScrap, Long> {

    // 특정 사용자가 스크랩한 게시물 조회
    @Query("SELECT ps.post FROM PostScrap ps WHERE ps.uid = :uid")
    List<Object[]> findScrappedPostsByUser(@Param("uid") Long uid);

    // 특정 게시물의 스크랩 수 조회
    @Query("SELECT COUNT(ps) FROM PostScrap ps WHERE ps.post.postId = :postId")
    int countScrapByPostId(@Param("postId") Long postId);

    // 특정 게시물이 특정 사용자에 의해 스크랩되었는지 확인
    boolean existsByPostIdAndAccessToken(Long postId, String accessToken);

    // 특정 게시물에 대해 스크랩 정보를 가져옴
    Optional<PostScrap> findByPostIdAndAccessToken(Long postId, String accessToken);
}
