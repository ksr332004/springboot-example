package com.example.basic.service.impl;

import com.example.basic.domain.Board;
import com.example.basic.dto.BoardRequest;
import com.example.basic.exception.ResourceNotFoundException;
import com.example.basic.repository.BoardRepository;
import com.example.basic.repository.jpa.JpaBasicPaginationBoardRepository;
import com.example.basic.repository.jpa.JpaUseSpecificationBoardRepository;
import com.example.basic.repository.jpa.JpaUseSpecificationBoardSpecification;
import com.example.basic.repository.querydsl.QuerydslBasicPaginationBoardRepository;
import com.example.basic.repository.querydsl.QuerydslCustomizedBoardRepository;
import com.example.basic.repository.querydsl.QuerydslUsePredicateBoardPredicate;
import com.example.basic.repository.querydsl.QuerydslUsePredicateBoardRepository;
import com.example.basic.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final JpaBasicPaginationBoardRepository jpaBasicPaginationBoardRepository;
    private final JpaUseSpecificationBoardRepository jpaUseSpecificationBoardRepository;
    private final QuerydslBasicPaginationBoardRepository querydslBasicPaginationBoardRepository;
    private final QuerydslCustomizedBoardRepository querydslCustomizedBoardRepository;
    private final QuerydslUsePredicateBoardRepository querydslUsePredicateBoardRepository;

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
    public Page<Board> jpaBasicPaginationFindAll(Pageable pageable) {
        return jpaBasicPaginationBoardRepository.findAll(pageable);
    }

    @Override
    public Page<Board> jpaUsePredicateFindByCriteria(BoardRequest boardRequest, Pageable pageable) {
        return jpaUseSpecificationBoardRepository.findAll(JpaUseSpecificationBoardSpecification.search(boardRequest), pageable);
    }

    @Override
    public Page<Board> querydslBasicPaginationFindAll(Pageable pageable) {
        return querydslBasicPaginationBoardRepository.findAll(pageable);
    }

    @Override
    public Page<Board> querydslCustomizedFindByCriteria(BoardRequest boardRequest, Pageable pageable) {
        return querydslCustomizedBoardRepository.findByCriteria(boardRequest, pageable);
    }

    @Override
    public Page<Board> qureydslUsePredicateFindByCriteria(BoardRequest boardRequest, Pageable pageable) {
        return querydslUsePredicateBoardRepository.findAll(QuerydslUsePredicateBoardPredicate.search(boardRequest), pageable);
    }

    @Override
    public Board updateBoard(Board board) {
        return boardRepository.findById(board.getId()).map( existingBoard -> {
            existingBoard.builder()
                    .name(board.getName())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .build();
            return boardRepository.save(existingBoard);
        }).orElseThrow(() -> new ResourceNotFoundException("Board Id" + board.getId() + " does not exists!"));
    }

    @Override
    public ResponseEntity deleteBoardById(Long id) {
        return boardRepository.findById(id).map(existingBoard -> {
            boardRepository.delete(existingBoard);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Board Id" + id + " does not exists!"));
    }

}