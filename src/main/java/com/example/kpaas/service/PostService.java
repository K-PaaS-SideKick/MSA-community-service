package com.example.kpaas.service;

import com.example.kpaas.dto.request.*;
import com.example.kpaas.dto.response.*;
import com.example.kpaas.model.Comment;
import com.example.kpaas.model.Post;
import com.example.kpaas.repository.PostRepository;
import com.example.kpaas.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    public List<PostResponse> getPosts(int page, String sortBy) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(sortBy));
        Page<Post> postPage = postRepository.findAll(pageable);
        return postPage.stream().map(post -> new PostResponse(post.getPostId(), post.getTitle())).collect(Collectors.toList());
    }


    public List<PostResponse> searchPosts(SearchRequest searchRequest) {
        List<Post> posts = postRepository.searchPosts(searchRequest.getCategory(), searchRequest.getQuery());
        return posts.stream().map(post -> new PostResponse(post.getPostId(), post.getTitle())).collect(Collectors.toList());
    }

    public PostDetails getPostDetails(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        return convertToDetails(post);
    }

    public PostResponse createPost(String accessToken, CreatePostRequest request) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCategory(request.getCategory());
        post.setDate(LocalDate.now());
        postRepository.save(post);
        return new PostResponse(post.getPostId(), post.getTitle());
    }

    public PostResponse updatePost(Long postId, String accessToken, UpdatePostRequest request) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCategory(request.getCategory());
        postRepository.save(post);
        return new PostResponse(post.getPostId(), post.getTitle());
    }

    public void deletePost(Long postId, String accessToken) {
        postRepository.deleteById(postId);
    }

    public LikeResponse likePost(String accessToken, LikeRequest request) {
        Post post = postRepository.findById(request.getPostId()).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setLike(post.getLike() + 1);
        postRepository.save(post);
        return new LikeResponse(post.getPostId(), post.getLike());
    }

    public CommentResponse addComment(String accessToken, CommentRequest request) {
        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setPostId(request.getPostId());
        comment.setParentComment(request.getParentComment() != null ? commentRepository.findById(request.getParentComment()).orElse(null) : null);
        commentRepository.save(comment);

        Post post = postRepository.findById(request.getPostId()).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setComments(post.getComments() + 1);
        postRepository.save(post);

        return new CommentResponse(); // 필요한 정보를 설정해 주세요
    }

    public ScrapResponse scrapPost(Long postId, String accessToken) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setScraps(post.getScraps() + 1);
        postRepository.save(post);
        return new ScrapResponse(post.getPostId(), post.getScraps());
    }

    public ScrapResponse unscrapPost(Long postId, String accessToken) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
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
