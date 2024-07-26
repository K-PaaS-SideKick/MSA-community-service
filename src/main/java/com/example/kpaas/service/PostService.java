package com.example.kpaas.service;

import com.example.kpaas.dto.request.*;
import com.example.kpaas.dto.response.*;
import com.example.kpaas.model.Comment;
import com.example.kpaas.model.Post;
import com.example.kpaas.repository.PostRepository;
import com.example.kpaas.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public List<PostResponse> getPosts(int page, String sortBy) {
        // Logic to fetch and return posts based on page and sorting
        List<Post> posts = postRepository.findAll(); // Add pagination and sorting
        return posts.stream().map(post -> new PostResponse(post.getPostId(), post.getTitle())).collect(Collectors.toList());
    }

    public List<PostResponse> searchPosts(SearchRequest searchRequest) {
        List<Post> posts = postRepository.searchPosts(searchRequest.getCategory(), searchRequest.getQuery());
        return posts.stream().map(post -> new PostResponse(post.getPostId(), post.getTitle())).collect(Collectors.toList());
    }

    public PostDetails getPostDetails(Long postId) {
        // Logic to fetch and return post details
        Post post = postRepository.findById(postId).orElseThrow();
        return convertToDetails(post);
    }

    public PostResponse createPost(String accessToken, CreatePostRequest request) {
        // Logic to create a new post
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCategory(request.getCategory());
        post.setDate(LocalDate.now());
        // Add other fields and logic
        postRepository.save(post);
        return new PostResponse(post.getPostId(), post.getTitle());
    }

    public PostResponse updatePost(Long postId, String accessToken, UpdatePostRequest request) {
        // Logic to update an existing post
        Post post = postRepository.findById(postId).orElseThrow();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCategory(request.getCategory());
        // Add other fields and logic
        postRepository.save(post);
        return new PostResponse(post.getPostId(), post.getTitle());
    }

    public void deletePost(Long postId, String accessToken) {
        // Logic to delete a post
        postRepository.deleteById(postId);
    }

    public LikeResponse likePost(String accessToken, LikeRequest request) {
        // Logic to handle post likes
        Post post = postRepository.findById(request.getPostId()).orElseThrow();
        post.setLike(post.getLike() + 1);
        postRepository.save(post);
        return new LikeResponse(post.getPostId(), post.getLike());
    }

    public CommentResponse addComment(String accessToken, CommentRequest request) {
        // Logic to add a comment to a post
        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setPostId(request.getPostId());
        comment.setParentComment(request.getParentComment() != null ? commentRepository.findById(request.getParentComment()).orElse(null) : null);
        // Add other fields and logic
        commentRepository.save(comment);
        // Update the post's comment count
        Post post = postRepository.findById(request.getPostId()).orElseThrow();
        post.setComments(post.getComments() + 1);
        postRepository.save(post);
        return new CommentResponse();
    }

/*    private CommentResponse convertToCommentResponse(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.setCommentId(comment.getCommentId());
        response.setContent(comment.getContent());
        response.setPostId(comment.getPostId());
        response.setUid(comment.getUid());
        response.setNickname(comment.getNickname());
        response.setLikes(comment.getLikes());
        // 필요한 경우 부모 댓글 정보 설정
        return response;
    }
*/

    public ScrapResponse scrapPost(Long postId, String accessToken) {
        // Logic to scrap a post
        Post post = postRepository.findById(postId).orElseThrow();
        post.setScraps(post.getScraps() + 1);
        postRepository.save(post);
        return new ScrapResponse(post.getPostId(), post.getScraps());
    }

    public ScrapResponse unscrapPost(Long postId, String accessToken) {
        // Logic to unscrap a post
        Post post = postRepository.findById(postId).orElseThrow();
        post.setScraps(post.getScraps() - 1);
        postRepository.save(post);
        return new ScrapResponse(post.getPostId(), post.getScraps());
    }

    private PostDetails convertToDetails(Post post) {
        PostDetails details = new PostDetails();
        details.setPostId(post.getPostId());
        details.setTitle(post.getTitle());
        details.setContent(post.getContent());
        details.setDate(post.getDate());
        details.setView(post.getView());
        details.setLike(post.getLike());
        details.setScraps(post.getScraps());
        details.setComments(post.getComments());
        details.setCategory(post.getCategory());
        return details;
    }
}
