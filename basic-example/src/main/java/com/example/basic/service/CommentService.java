package com.example.basic.service;

import com.example.basic.domain.Comment;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface CommentService {
    Optional<Comment> findCommentById(Long id);
    Comment saveComment(Long boardId, Comment comment);
    ResponseEntity deleteCommentByBoardIdAndCommentId(Long boardId, Long commentId);
}