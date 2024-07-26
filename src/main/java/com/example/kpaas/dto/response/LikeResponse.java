package com.example.kpaas.dto.response;

public class LikeResponse {
    private Long postId;
    private int likes;

    public LikeResponse(Long postId, int likes) {
        this.postId = postId;
        this.likes = likes;
    }

    // Getters and setters
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
