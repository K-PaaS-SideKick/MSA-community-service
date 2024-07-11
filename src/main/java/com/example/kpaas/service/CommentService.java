package com.example.kpaas.service;

import com.example.kpaas.dto.request.CommentLikeRequest;
import com.example.kpaas.dto.response.CommentLikeResponse;
import com.example.kpaas.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public CommentLikeResponse likeComment(String accessToken, CommentLikeRequest request) {
        // Implement comment liking logic
        return null;
    }

    public void deleteComment(Long commentId, String accessToken) {
        // Implement comment deletion logic
    }
}