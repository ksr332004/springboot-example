package com.example.basic.service;

import com.example.basic.domain.Comment;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    Comment saveComment(Long boardId, Comment comment);
    ResponseEntity deleteCommentById(Long boardId, Long commentId);
}