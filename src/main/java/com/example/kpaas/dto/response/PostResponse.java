package com.example.kpaas.dto.response;

public class PostResponse {
    private Long postId;
    private String title;
    private String content;
    private int like;
    private int scraps;
    private int comments;
    private String category;

    public PostResponse(Long postId, String title) {
        this.postId = postId;
        this.title = title;
    }

    // Getters and setters
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
