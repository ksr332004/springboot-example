package com.example.basic.service.impl;

import com.example.basic.domain.Comment;
import com.example.basic.exception.ResourceNotFoundException;
import com.example.basic.repository.BoardRepository;
import com.example.basic.repository.CommentRepository;
import com.example.basic.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;


    @Override
    public Optional<Comment> findCommentById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    @Transactional
    public Comment saveComment(Long boardId, Comment comment) {
        return boardRepository.findById(boardId).map( b -> {
            comment.setBoard(b);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("Board Id" + boardId + " does not exists!"));
    }

    @Override
    @Transactional
    public ResponseEntity deleteCommentByBoardIdAndCommentId(Long boardId, Long commentId) {
        return boardRepository.findById(boardId).map( b -> {
            b.getComments().removeIf( c -> commentId.equals(c.getId()) );
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Board Id" + boardId + " does not exists!"));
    }
}