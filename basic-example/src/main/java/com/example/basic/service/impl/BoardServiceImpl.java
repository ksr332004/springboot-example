package com.example.basic.service.impl;

import com.example.basic.domain.Board;
import com.example.basic.repository.BasicPaginationRepository;
import com.example.basic.repository.BoardRepository;
import com.example.basic.repository.QuerydslPaginationRepository;
import com.example.basic.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Board> updateBoard = boardRepository.findById(board.getId());

        if (!updateBoard.isPresent()) {
            return null;
        }

        updateBoard.get().setName(board.getName());
        updateBoard.get().setTitle(board.getTitle());
        updateBoard.get().setContent(board.getContent());

        return boardRepository.save(updateBoard.get());
    }

    @Override
    public void deleteBoardById(Long id) {
        Optional<Board> deleteBoard = boardRepository.findById(id);
        if (deleteBoard.isPresent()) {
            boardRepository.delete(deleteBoard.get());
        }
    }
}
