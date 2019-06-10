package com.example.basic.repository.querydsl;

import com.example.basic.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuerydslCustomizedBoardRepositoryCustom {
    Page<Board> findByCriteria(String name, String title, String content, Pageable pageable);
}