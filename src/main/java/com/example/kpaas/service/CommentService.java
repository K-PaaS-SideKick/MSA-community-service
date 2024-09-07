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

    public void deleteComment(Long commentId, String accessToken) {
        // 댓글 삭제 로직
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepository.delete(comment);
    }

    public List<CommentResponse> getCommentsByPost(Long postId) {
        // 특정 게시물에 대한 댓글 가져오기
        List<Comment> comments = commentRepository.findCommentsByPostId(postId);
        return comments.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    public CommentResponse addReply(Long postId, Long parentCommentId, CommentRequest request) {
        // 답글 추가 로직
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Comment parentComment = commentRepository.findById(parentCommentId).orElseThrow(() -> new RuntimeException("Parent comment not found"));

        Comment reply = new Comment();
        reply.setContent(request.getContent());
        reply.setPost_in_Commnent(post);
        reply.setParentComment(parentComment);

        commentRepository.save(reply);

        // 댓글 수 업데이트
        post.setCommentsCount(post.getCommentsCount() + 1);
        postRepository.save(post);

        return new CommentResponse(reply.getCommentId(), reply.getContent(), reply.getCreatedAt());
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
