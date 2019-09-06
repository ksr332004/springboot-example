package com.example.board.service;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.CommentDTO;
import com.example.board.entity.Board;
import com.example.board.exception.BusinessException;
import com.example.board.exception.ErrorCode;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardDTO.ResponseToList> selectBoardList() {
        return boardRepository.findAll()
                .stream()
                .map(b -> BoardDTO.ResponseToList.builder()
                        .id(b.getId())
                        .title(b.getTitle())
                        .createDate(b.getCreateDate()).build())
                .collect(Collectors.toList());
    }

    @Transactional
    public BoardDTO.ResponseToDetail selectBoard(Long id) {
        return boardRepository.findById(id)
                .map(b -> BoardDTO.ResponseToDetail.builder()
                        .id(b.getId())
                        .title(b.getTitle())
                        .content(b.getContent())
                        .createDate(b.getCreateDate())
                        .comments(b.getComments()
                                .stream()
                                .map(c -> CommentDTO.ResponseToDetail.builder()
                                        .id(c.getId())
                                        .content(c.getContent())
                                        .createDate(c.getCreateDate()).build()
                                )
                                .collect(Collectors.toList())
                        ).build())
                .orElseThrow(() -> new BusinessException(ErrorCode.BOARD_NOT_EXIST));
    }

    @Transactional
    public BoardDTO.ResponseToDetail insertBoard(BoardDTO.RequestToCreate boardDTO) {
        Board board = boardRepository.save(
                Board.builder()
                        .title(boardDTO.getTitle())
                        .content(boardDTO.getContent())
                        .build());
        return BoardDTO.ResponseToDetail.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createDate(board.getCreateDate())
                .build();
    }

    @Transactional
    public BoardDTO.ResponseToDetail updateBoard(BoardDTO.RequestToUpdate boardDTO) {
        Board board = boardRepository.findById(boardDTO.getId())
                .map(b -> {
                    b.setTitle(boardDTO.getTitle());
                    b.setContent(boardDTO.getContent());
                    return boardRepository.save(b);
                })
                .orElseThrow(() -> new BusinessException(ErrorCode.BOARD_NOT_EXIST));
        return BoardDTO.ResponseToDetail.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createDate(board.getCreateDate())
                .comments(board.getComments().stream()
                        .map(c -> CommentDTO.ResponseToDetail.builder()
                                .id(c.getId())
                                .content(c.getContent())
                                .createDate(c.getCreateDate()).build())
                        .collect(Collectors.toList())
                ).build();
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

}
