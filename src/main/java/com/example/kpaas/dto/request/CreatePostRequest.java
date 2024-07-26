package com.example.kpaas.dto.request;

import java.util.List;

public class CreatePostRequest {
    private String title;
    private List<byte[]> projectImg;
    private String content;
    private List<String> category;

    // Getters and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<byte[]> getProjectImg() {
        return projectImg;
    }

    public void setProjectImg(List<byte[]> projectImg) {
        this.projectImg = projectImg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }
}
