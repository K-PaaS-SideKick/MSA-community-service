package com.example.kpaas.util;

import com.example.kpaas.dto.response.CommentResponse;
import com.example.kpaas.model.Comment;

public class DTOConverter {

    public static CommentResponse convertToCommentResponse(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.setCommentId(comment.getCommentId());
        response.setContent(comment.getContent());
        response.setCreatedAt(comment.getCreatedAt());
        response.setParentComment(comment.getParentComment() != null ? comment.getParentComment().getCommentId() : null);
        // Set other fields
        return response;
    }
}
