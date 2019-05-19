package com.example.basic.service.impl;

import com.example.basic.domain.Board;
import com.example.basic.exception.ResourceNotFoundException;
import com.example.basic.repository.BasicPaginationRepository;
import com.example.basic.repository.BoardRepository;
import com.example.basic.repository.QuerydslPaginationRepository;
import com.example.basic.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final BasicPaginationRepository basicPaginationRepository;
    private final QuerydslPaginationRepository querydslPaginationRepository;

    public BoardServiceImpl(BoardRepository boardRepository
            , BasicPaginationRepository basicPaginationRepository
            , QuerydslPaginationRepository querydslPaginationRepository) {
        this.boardRepository = boardRepository;
        this.basicPaginationRepository = basicPaginationRepository;
        this.querydslPaginationRepository = querydslPaginationRepository;
    }

    @Override
    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public Optional<Board> findBoardById(Long id) {
        return boardRepository.findById(id);
    }

    @Override
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Override
    public Page<Board> basicPaginationFindAll(Pageable pageable) {
        return basicPaginationRepository.findAll(pageable);
    }

    @Override
    public Page<Board> querydslPaginationFindAll(Pageable pageable) {
        return querydslPaginationRepository.findAll(pageable);
    }

    @Override
    public Board updateBoard(Board board) {
        return boardRepository.findById(board.getId()).map( existingBoard -> {
                    existingBoard.setName(board.getName());
                    existingBoard.setContent(board.getContent());
                    existingBoard.setTitle(board.getTitle());
                    return boardRepository.save(existingBoard);
                }
        ).orElse(new Board());
        //.orElseThrow(() -> new ResourceNotFoundException("Board Id" + board.getId() + " does not exists!"));
    }

    @Override
    public ResponseEntity deleteBoardById(Long id) {
        return boardRepository.findById(id).map(existingBoard -> {
            boardRepository.delete(existingBoard);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Board Id" + id + " does not exists!"));
    }

}