package com.example.basic.service;


import com.example.basic.domain.Board;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    Board saveBoard(Board board);
    Optional<Board> findBoardById(Long id);
    List<Board> findAll();
    Board updateBoard(Board board);
    void deleteBoardById(Long id);
}
