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
    public CommentDTO.ResponseToDetail insertComment(Long id, CommentDTO.RequestToCreate commentDTO) {
        Comment comment = boardRepository.findById(id)
                .map(b -> commentRepository.save(
                        Comment.builder()
                                .content(commentDTO.getContent())
                                .board(b).build()))
                .orElseThrow(() -> new BusinessException(ErrorCode.BOARD_NOT_EXIST));

        return CommentDTO.ResponseToDetail.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createDate(comment.getCreateDate())
                .build();
    }

    @Transactional
    public CommentDTO.ResponseToDetail updateComment(CommentDTO.RequestToUpdate commentDTO) {
        Comment comment = commentRepository.findById(commentDTO.getId())
                .map(c -> {
                    c.setContent(commentDTO.getContent());
                    return commentRepository.save(c);
                })
                .orElseThrow(() -> new BusinessException(ErrorCode.COMMENT_NOT_EXIST));

        return CommentDTO.ResponseToDetail.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createDate(comment.getCreateDate())
                .build();
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
