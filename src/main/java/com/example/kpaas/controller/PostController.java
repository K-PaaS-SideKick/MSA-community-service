package com.example.kpaas.controller;

import com.example.kpaas.dto.request.*;
import com.example.kpaas.dto.response.*;
import com.example.kpaas.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<PostResponse>> getPosts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "recent") String sortBy) {
        return ResponseEntity.ok(postService.getPosts(page, sortBy));
    }

    @PostMapping("/search")
    public ResponseEntity<List<PostResponse>> searchPosts(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(postService.searchPosts(searchRequest));
    }

    @GetMapping("/details")
    public ResponseEntity<PostDetails> getPostDetails(@RequestParam Long postId) {
        return ResponseEntity.ok(postService.getPostDetails(postId));
    }

    @PostMapping("/new")
    public ResponseEntity<PostResponse> createPost(
            @RequestHeader("access") String accessToken,
            @RequestBody CreatePostRequest request) {
        return ResponseEntity.ok(postService.createPost(accessToken, request));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable Long postId,
            @RequestHeader("access") String accessToken,
            @RequestBody UpdatePostRequest request) {
        return ResponseEntity.ok(postService.updatePost(postId, accessToken, request));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long postId,
            @RequestHeader("access") String accessToken) {
        postService.deletePost(postId, accessToken);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/upvote")
    public ResponseEntity<UpvoteResponse> UpvotePost(
            @RequestHeader("access") String accessToken,
            @RequestBody UpvoteRequest request) {
        return ResponseEntity.ok(postService.upvoteResponse(accessToken, request));
    }

    @PostMapping("/comment")
    public ResponseEntity<CommentResponse> addComment(
            @RequestHeader("access") String accessToken,
            @RequestBody CommentRequest request) {
        return ResponseEntity.ok(postService.addComment(accessToken, request));
    }

    @PostMapping("/{postId}/scrap")
    public ResponseEntity<ScrapResponse> scrapPost(
            @PathVariable Long postId,
            @RequestHeader("access") String accessToken) {
        return ResponseEntity.ok(postService.scrapPost(postId, accessToken));
    }

    @DeleteMapping("/{postId}/scrap")
    public ResponseEntity<ScrapResponse> unscrapPost(
            @PathVariable Long postId,
            @RequestHeader("access") String accessToken) {
        return ResponseEntity.ok(postService.unscrapPost(postId, accessToken));
    }
}
