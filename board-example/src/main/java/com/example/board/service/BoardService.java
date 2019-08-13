package com.example.board.service;

import com.example.board.entity.Board;
import com.example.board.exception.BusinessException;
import com.example.board.exception.ErrorCode;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> selectBoardList() {
        return boardRepository.findAll();
    }

    public void insertBoard(Board board) {
        boardRepository.save(board);
    }

    public Board updateBoard(Board board) {
        Optional<Board> b = boardRepository.findById(board.getId());
        return b.map( x -> {
            x.setTitle(board.getTitle());
            x.setContent(board.getContent());
            return boardRepository.save(x);
        }).orElseThrow(() -> new BusinessException(ErrorCode.BOARD_NOT_EXIST));
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

}
