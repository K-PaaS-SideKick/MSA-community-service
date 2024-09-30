package com.example.kpaas.dto.request;

import com.example.kpaas.model.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {
    private Long commentId;
    private Post post;
    private Long postId;
    private String content;
    private Long parentComment;
}
