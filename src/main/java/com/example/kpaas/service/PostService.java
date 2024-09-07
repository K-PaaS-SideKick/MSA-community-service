package com.example.kpaas.service;

import com.example.kpaas.dto.request.*;
import com.example.kpaas.dto.response.*;
import com.example.kpaas.model.*;
import com.example.kpaas.repository.CategoryRepository;
import com.example.kpaas.repository.PostRepository;
import com.example.kpaas.repository.CommentRepository;
import com.example.kpaas.repository.PostScrapRepository;
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

    @Autowired
    private PostScrapRepository postScrapRepository;


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

        List<Category> category = post.getCategories();

        return convertToDetails(post, category);
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
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // 사용자가 이미 스크랩했는지 확인
        if (postScrapRepository.existsByPostIdAndAccessToken(postId, accessToken)) {
            throw new RuntimeException("Post already scraped by the user");
        }

        PostScrap postScrap = new PostScrap();
        postScrap.setPost(post);
        postScrap.setAccessToken(accessToken);
        postScrapRepository.save(postScrap);

        post.setPostScrapCount(post.getPostScrapCount() + 1);  // 스크랩 수 증가
        postRepository.save(post);

        return new ScrapResponse(post.getPostId(), post.getPostScrapCount());
    }

    public ScrapResponse unscrapPost(Long postId, String accessToken) {
        // 게시물 스크랩 취소 처리
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // 스크랩한 기록을 확인
        PostScrap postScrap = postScrapRepository.findByPostIdAndAccessToken(postId, accessToken)
                .orElseThrow(() -> new RuntimeException("Scrap not found"));

        postScrapRepository.delete(postScrap);  // 스크랩 삭제

        post.setPostScrapCount(post.getPostScrapCount() - 1);  // 스크랩 수 감소
        postRepository.save(post);

        return new ScrapResponse(post.getPostId(), post.getPostScrapCount());
    }

    private PostDetails convertToDetails(Post post, List<Category> category) {
        // 게시물 상세 정보를 변환
        PostDetails details = new PostDetails();
        details.setPostId(post.getPostId());
        details.setTitle(post.getTitle());
        details.setContent(post.getContent());
        details.setDate(post.getCreatedAtDate());
        details.setView(post.getView());
        details.setLike(post.getLike());
        details.setScraps(post.getPostScrapCount());
        details.setComments(post.getCommentsCount());
        List<String> categoryNames = category.stream()
                .map(Category::getCategoryName)  // Category 객체에서 categoryName 추출
                .collect(Collectors.toList());
        details.setCategory(categoryNames);
        return details;
    }
}
