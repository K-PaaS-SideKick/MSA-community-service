package com.example.kpaas.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class PostDetails {
    private Long postId;
    private String title;
    private byte[] image;
    private String content;
    private int view;
    private int upvote;
    private int scraps;
    private int comments;
    private LocalDateTime date;
    private List<CommentResponse> commentList;
    private List<String> category;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getUpvote() {
        return upvote;
    }

    public void setUpvote(int upvote) {
        this.upvote = upvote;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<CommentResponse> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentResponse> commentList) {
        this.commentList = commentList;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }
}
