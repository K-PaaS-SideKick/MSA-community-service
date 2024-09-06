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
        // 좋아요 기능 로직
        Comment comment = commentRepository.findById(request.getCommentId()).orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setLikes(comment.getLikes() + 1);
        commentRepository.save(comment);
        return new CommentLikeResponse(comment.getCommentId(), comment.getLikes());
    }

    public void deleteComment(Long commentId, String accessToken) {
        // 댓글 삭제 로직
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepository.delete(comment);
    }

    public List<CommentResponse> getCommentsByPost(Long postId) {
        // 특정 게시물에 대한 댓글 가져오기
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    private CommentResponse convertToResponse(Comment comment) {
        // 댓글을 응답 객체로 변환
        CommentResponse response = new CommentResponse();
        response.setCommentId(comment.getCommentId());
        response.setContent(comment.getContent());
        response.setCreatedAt(comment.getCreatedAt());
        response.setParentComment(comment.getParentComment() != null ? comment.getParentComment().getCommentId() : null);
        response.setLikes(comment.getLikes());
        // 필요한 다른 필드 설정
        return response;
    }
}
