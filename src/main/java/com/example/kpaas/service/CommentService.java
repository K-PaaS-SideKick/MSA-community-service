package com.example.kpaas.service;

import com.example.kpaas.dto.request.CommentRequest;
import com.example.kpaas.dto.response.CommentLikeResponse;
import com.example.kpaas.dto.response.CommentResponse;
import com.example.kpaas.model.Comment;
import com.example.kpaas.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public CommentLikeResponse likeComment(String accessToken, CommentRequest request) {
        // Logic to handle comment likes
        Comment comment = commentRepository.findById(request.getCommentId()).orElseThrow();
        comment.setLikes(comment.getLikes() + 1);
        commentRepository.save(comment);
        return new CommentLikeResponse(comment.getCommentId(), comment.getLikes());
    }

    public void deleteComment(Long commentId, String accessToken) {
        // Logic to handle comment deletion
        commentRepository.deleteById(commentId);
    }

    public List<CommentResponse> getCommentsByPost(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    private CommentResponse convertToResponse(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.setCommentId(comment.getCommentId());
        response.setContent(comment.getContent());
        response.setCreatedAt(comment.getCreatedAt());
        response.setParentComment(comment.getParentComment() != null ? comment.getParentComment().getCommentId() : null);
        // Set other fields
        return response;
    }
}
