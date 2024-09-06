package com.example.kpaas.dto.request;

public class CreatePostRequest {

    private String title;       // 게시물 제목
    private String content;     // 게시물 내용
    private String categoryName; // 게시물 카테고리

    // Getters and Setters
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String category) {
        this.categoryName = category;
    }
}
