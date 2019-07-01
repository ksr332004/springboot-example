package com.example.basic.service.impl;

import com.example.basic.domain.Comment;
import com.example.basic.exception.ResourceNotFoundException;
import com.example.basic.repository.BoardRepository;
import com.example.basic.repository.CommentRepository;
import com.example.basic.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Override
    public Comment saveComment(Long boardId, Comment comment) {
        return boardRepository.findById(boardId).map( existingBoard -> {
            existingBoard.addComments(comment);
            comment.setBoard(existingBoard);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("Board Id" + boardId + " does not exists!"));
    }

    @Override
    public ResponseEntity deleteCommentById(Long boardId, Long commentId) {
        return boardRepository.findById(boardId).map( existingBoard -> {
            existingBoard.getComments().removeIf( targetComment -> commentId.equals(targetComment.getId()));
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Board Id" + boardId + " does not exists!"));
    }
}