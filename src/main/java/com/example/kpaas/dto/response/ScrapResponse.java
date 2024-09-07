package com.example.kpaas.dto.response;

public class ScrapResponse {

    private Long postId; // 게시물 ID
    private int scraps; // 현재 스크랩 수

    // Constructor
    public ScrapResponse(Long postId, int scraps) {
        this.postId = postId;
        this.scraps = scraps;
    }

    // Getters and Setters
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
