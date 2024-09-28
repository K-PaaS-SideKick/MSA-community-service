package com.example.kpaas.dto.response;

public class UpvoteResponse {
    private Long postId;
    private int upvotes;

    public UpvoteResponse(Long postId, int upvotes) {
        this.postId = postId;
        this.upvotes = upvotes;
    }

    // Getters and setters
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }
}
