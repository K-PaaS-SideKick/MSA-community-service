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
        List<PostResponse> posts = postService.getPosts(page, sortBy);
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/search")
    public ResponseEntity<List<PostResponse>> searchPosts(@RequestBody SearchRequest searchRequest) {
        List<PostResponse> posts = postService.searchPosts(searchRequest);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/details")
    public ResponseEntity<PostDetails> getPostDetails(@RequestParam Long postId) {
        PostDetails postDetails = postService.getPostDetails(postId);
        return ResponseEntity.ok(postDetails);
    }

    @PostMapping("/new")
    public ResponseEntity<PostResponse> createPost(
            @RequestHeader("access") String accessToken,
            @RequestBody CreatePostRequest request) {
        PostResponse postResponse = postService.createPost(accessToken, request);
        return ResponseEntity.ok(postResponse);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable Long postId,
            @RequestHeader("access") String accessToken,
            @RequestBody UpdatePostRequest request) {
        PostResponse postResponse = postService.updatePost(postId, accessToken, request);
        return ResponseEntity.ok(postResponse);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long postId,
            @RequestHeader("access") String accessToken) {
        postService.deletePost(postId, accessToken);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/like")
    public ResponseEntity<LikeResponse> likePost(
            @RequestHeader("access") String accessToken,
            @RequestBody LikeRequest request) {
        LikeResponse likeResponse = postService.likePost(accessToken, request);
        return ResponseEntity.ok(likeResponse);
    }

    @PostMapping("/comment")
    public ResponseEntity<CommentResponse> addComment(
            @RequestHeader("access") String accessToken,
            @RequestBody CommentRequest request) {
        CommentResponse commentResponse = postService.addComment(accessToken, request);
        return ResponseEntity.ok(commentResponse);
    }

    @PostMapping("/{postId}/scrap")
    public ResponseEntity<ScrapResponse> scrapPost(
            @PathVariable Long postId,
            @RequestHeader("access") String accessToken) {
        ScrapResponse scrapResponse = postService.scrapPost(postId, accessToken);
        return ResponseEntity.ok(scrapResponse);
    }

    @DeleteMapping("/{postId}/scrap")
    public ResponseEntity<ScrapResponse> unscrapPost(
            @PathVariable Long postId,
            @RequestHeader("access") String accessToken) {
        ScrapResponse scrapResponse = postService.unscrapPost(postId, accessToken);
        return ResponseEntity.ok(scrapResponse);
    }
}
