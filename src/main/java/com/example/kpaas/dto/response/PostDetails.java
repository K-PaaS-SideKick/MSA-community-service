package com.example.kpaas.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class PostDetails {

    private Long postId;       // 게시물 ID
    private String title;      // 게시물 제목
    private String content;    // 게시물 내용
    private LocalDateTime date;    // 게시물 작성 일시
    private int view;          // 조회 수
    private int like;          // 좋아요 수
    private int scraps;        // 스크랩 수
    private int comments;      // 댓글 수
    private List<String> category; // 게시물 카테고리

    // Getters and Setters
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getScraps() {
        return scraps;
    }

    public void setScraps(int scraps) {
        this.scraps = scraps;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }
}
