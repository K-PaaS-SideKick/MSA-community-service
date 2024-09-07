package com.example.kpaas.service;

import com.example.kpaas.dto.request.CommentRequest;
import com.example.kpaas.dto.response.CommentLikeResponse;
import com.example.kpaas.dto.response.CommentResponse;
import com.example.kpaas.model.Comment;
import com.example.kpaas.model.Post;
import com.example.kpaas.repository.CommentRepository;
import com.example.kpaas.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    public CommentLikeResponse likeComment(String accessToken, CommentRequest request) {
        // 좋아요 기능 로직
        Comment comment = commentRepository.findById(request.getCommentId()).orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setLikes(comment.getLikes() + 1);
        commentRepository.save(comment);
        return new CommentLikeResponse(comment.getCommentId(), comment.getLikes());
    }

    // 댓글 삭제 로직
    public void deleteComment(Long commentId, String accessToken) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepository.delete(comment);
        Post post = comment.getPost_in_Comment();
        post.setCommentsCount(post.getCommentsCount() - 1);  // 게시물의 댓글 수 업데이트
        postRepository.save(post);
    }

    // 게시물에 대한 댓글 및 답글 가져오기
    public List<CommentResponse> getCommentsByPost(Long postId) {
        List<Comment> comments = commentRepository.findCommentsByPostId(postId);

        // 댓글과 답글 계층 구조로 변환
        List<CommentResponse> responses = comments.stream()
                .filter(comment -> comment.getParentComment() == null)  // 부모 댓글만 필터링
                .map(this::convertToResponseWithReplies)  // 댓글과 답글을 함께 변환
                .collect(Collectors.toList());
        return responses;
    }

    // 답글 추가 로직
    public CommentResponse addReply(Long postId, Long parentCommentId, CommentRequest request) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Comment parentComment = commentRepository.findById(parentCommentId).orElseThrow(() -> new RuntimeException("Parent comment not found"));

        Comment reply = new Comment();
        reply.setContent(request.getContent());
        reply.setPost_in_Commnent(post);
        reply.setParentComment(parentComment);

        commentRepository.save(reply);

        post.setCommentsCount(post.getCommentsCount() + 1);  // 댓글 수 업데이트
        postRepository.save(post);

        return new CommentResponse(reply.getCommentId(), reply.getContent(), reply.getCreatedAt());
    }

    // 댓글과 답글 계층 구조로 변환하는 메서드
    private CommentResponse convertToResponseWithReplies(Comment comment) {
        CommentResponse response = convertToResponse(comment);

        // 대댓글 가져오기
        List<CommentResponse> childComments = comment.getChildComments().stream()
                .map(this::convertToResponse)  // 대댓글도 CommentResponse로 변환
                .collect(Collectors.toList());

        response.setChildComments(childComments);  // 답글 리스트 추가
        return response;
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
