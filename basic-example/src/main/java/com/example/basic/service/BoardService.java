package com.example.basic.service;

import com.example.basic.domain.Board;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    Board saveBoard(Board board);
    Optional<Board> findBoardById(Long id);
    Optional<Board> findBoardByTitle(String title);
    List<Board> findAll();
    Board updateBoard(Board board);
    ResponseEntity deleteBoardById(Long id);
}
