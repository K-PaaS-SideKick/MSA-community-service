package com.example.kpaas.service;

import com.example.kpaas.dto.request.*;
import com.example.kpaas.dto.response.*;
import com.example.kpaas.model.*;
import com.example.kpaas.repository.PostRepository;
import com.example.kpaas.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    public List<PostResponse> getPosts(int page, String sortBy) {
        // 페이지네이션과 정렬 처리
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(sortBy));
        Page<Post> postPage = postRepository.findAll(pageable);
        return postPage.stream().map(post -> new PostResponse(post.getPostId(), post.getTitle())).collect(Collectors.toList());
    }

    public List<PostResponse> searchPosts(SearchRequest searchRequest) {
        // 검색 기능 구현
        List<Post> posts = postRepository.searchPosts(searchRequest.getCategory(), searchRequest.getQuery());
        return posts.stream().map(post -> new PostResponse(post.getPostId(), post.getTitle())).collect(Collectors.toList());
    }

    public PostDetails getPostDetails(Long postId) {
        // 게시물 상세보기
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));

        List<Comment> comments = commentRepository.findByPostId(postId);
        List<Category> category = CategoryRepository.findByPostId(postId);
        List<PostScrap> scraps = postScrapRepository.findByPostId(postId);
        return convertToDetails(post, category, scraps);
    }

    public PostResponse createPost(String accessToken, CreatePostRequest request) {
        // 게시물 생성
        Post post = new Post();
        Category category = new Category();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        category.setCategoryName(request.getCategoryName());
        post.setDate(LocalDateTime.from(LocalDate.now()));
        postRepository.save(post);
        return new PostResponse(post.getPostId(), post.getTitle());
    }

    public PostResponse updatePost(Long postId, String accessToken, UpdatePostRequest request) {
        // 게시물 수정
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Category category = new Category();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        category.setCategoryName(request.getCategoryName());
        postRepository.save(post);
        return new PostResponse(post.getPostId(), post.getTitle());
    }

    public void deletePost(Long postId, String accessToken) {
        // 게시물 삭제
        postRepository.deleteById(postId);
    }

    public LikeResponse likePost(String accessToken, LikeRequest request) {
        // 게시물 좋아요 처리
        Post post = postRepository.findById(request.getPostId()).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setLike(post.getLike() + 1);
        postRepository.save(post);
        return new LikeResponse(post.getPostId(), post.getLike());
    }

    public CommentResponse addComment(String accessToken, CommentRequest request) {
        // 댓글 추가
        Post post = postRepository.findById(request.getPostId_Comment()).orElseThrow(() -> new RuntimeException("Post not found"));
        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setPost_in_Commnent(post);
        comment.setParentComment(request.getParentComment() != null ? commentRepository.findById(request.getParentComment()).orElse(null) : null);
        commentRepository.save(comment);

        post.setCommentsCount(post.getCommentsCount() + 1);  // 댓글 수 업데이트
        postRepository.save(post);

        return new CommentResponse(comment.getCommentId(), comment.getContent(), comment.getCreatedAt());
    }

    public ScrapResponse scrapPost(Long postId, String accessToken) {
        // 게시물 스크랩 처리
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        PostScrap postScrap = new PostScrap();
        postScrap.setScraps(postScrap.getScraps() + 1);
        postRepository.save(post);
        return new ScrapResponse(post.getPostId(), postScrap.getScraps());
    }

    public ScrapResponse unscrapPost(Long postId, String accessToken) {
        // 게시물 스크랩 취소 처리
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        PostScrap postScrap = new PostScrap();
        postScrap.setScraps(postScrap.getScraps() - 1);
        postRepository.save(post);
        return new ScrapResponse(post.getPostId(), postScrap.getScraps());
    }

    private PostDetails convertToDetails(Post post, List<Category> category, List<PostScrap> postScrap) {
        // 게시물 상세 정보를 변환
        PostDetails details = new PostDetails();
        details.setPostId(post.getPostId());
        details.setTitle(post.getTitle());
        details.setContent(post.getContent());
        details.setDate(post.getCreatedAtDate());
        details.setView(post.getView());
        details.setLike(post.getLike());
        details.setScraps(postScrap.getScraps());
        details.setComments(post.getCommentsCount());
        details.setCategory(category.getCategoryName());
        return details;
    }
}
