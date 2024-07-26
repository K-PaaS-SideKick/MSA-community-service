package com.example.kpaas.dto.response;

public class ScrapResponse {
    private Long postId;
    private int scraps;

    public ScrapResponse(Long postId, int scraps) {
        this.postId = postId;
        this.scraps = scraps;
    }

    // Getters and setters
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public int getScraps() {
        return scraps;
    }

    public void setScraps(int scraps) {
        this.scraps = scraps;
    }
}
