package com.example.basic.service;

import com.example.basic.domain.Board;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    Board saveBoard(Board board);

    Optional<Board> findBoardById(Long id);
    List<Board> findAll();
    Page<Board> basicPaginationFindAll(Pageable pageable);
    Page<Board> querydslPaginationFindAll(Pageable pageable);

    Board updateBoard(Board board);
    ResponseEntity deleteBoardById(Long id);
}
