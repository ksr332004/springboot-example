package com.example.basic.repository.querydsl;

import com.example.basic.domain.Board;
import com.example.basic.dto.BoardRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuerydslCustomizedBoardRepositoryCustom {
    Page<Board> findByCriteria(BoardRequest boardRequest, Pageable pageable);
}