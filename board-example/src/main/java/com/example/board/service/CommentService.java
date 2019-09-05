package com.example.board.service;

import com.example.board.dto.CommentDTO;
import com.example.board.entity.Comment;
import com.example.board.exception.BusinessException;
import com.example.board.exception.ErrorCode;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Comment insertComment(Long id, CommentDTO.RequestToCreate commentDTO) {
        Comment comment = Comment.builder().content(commentDTO.getContent()).build();
        return boardRepository.findById(id)
                .map(b -> {
                    comment.setBoard(b);
                    return commentRepository.save(comment);
                })
                .orElseThrow(() -> new BusinessException(ErrorCode.BOARD_NOT_EXIST));
    }

    @Transactional
    public Comment updateComment(CommentDTO.RequestToUpdate commentDTO) {
        Comment comment = Comment.builder().id(commentDTO.getId()).content(commentDTO.getContent()).build();
        return commentRepository.findById(comment.getId())
                .map(c -> {
                    c.setContent(comment.getContent());
                    return commentRepository.save(comment);
                })
                .orElseThrow(() -> new BusinessException(ErrorCode.COMMENT_NOT_EXIST));
    }

    @Transactional
    public void deleteComment(Long boardId, Long commentId) {
        boardRepository.findById(boardId)
                .map(b -> {
                    b.getComments().removeIf(c -> commentId.equals(c.getId()));
                    return boardRepository.save(b);
                })
                .orElseThrow(() -> new BusinessException(ErrorCode.BOARD_NOT_EXIST));
    }

}
