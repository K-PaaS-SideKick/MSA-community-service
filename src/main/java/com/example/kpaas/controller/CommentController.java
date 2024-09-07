package com.example.kpaas.controller;

import com.example.kpaas.dto.request.CommentRequest;
import com.example.kpaas.dto.response.CommentLikeResponse;
import com.example.kpaas.dto.response.CommentResponse;
import com.example.kpaas.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 특정 게시물의 댓글 및 답글 가져오기
    @GetMapping("/post/{postId}")
    public List<CommentResponse> getCommentsByPost(@PathVariable Long postId) {
        return commentService.getCommentsByPost(postId);
    }

    // 답글 추가
    @PostMapping("/post/{postId}/reply/{parentCommentId}")
    public CommentResponse addReply(@PathVariable Long postId, @PathVariable Long parentCommentId, @RequestBody CommentRequest request, @RequestHeader("Authorization") String accessToken) {
        return commentService.addReply(postId, parentCommentId, request);
    }

    // 댓글 좋아요
    @PostMapping("/like")
    public ResponseEntity<CommentLikeResponse> likeComment(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody CommentRequest request) {
        CommentLikeResponse response = commentService.likeComment(accessToken, request);
        return ResponseEntity.ok(response);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId,
            @RequestHeader("Authorization") String accessToken) {
        commentService.deleteComment(commentId, accessToken);
        return ResponseEntity.noContent().build();
    }
}
