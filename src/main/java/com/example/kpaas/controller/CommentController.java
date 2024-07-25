package com.example.kpaas.controller;

import com.example.kpaas.dto.request.CommentRequest;
import com.example.kpaas.dto.response.CommentLikeResponse;
import com.example.kpaas.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/like")
    public ResponseEntity<CommentLikeResponse> likeComment(
            @RequestHeader("access") String accessToken,
            @RequestBody CommentRequest request) {
        return ResponseEntity.ok(commentService.likeComment(accessToken, request));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId,
            @RequestHeader("access") String accessToken) {
        commentService.deleteComment(commentId, accessToken);
        return ResponseEntity.noContent().build();
    }
}