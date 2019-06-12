package com.example.basic.repository.querydsl;

import com.example.basic.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface QuerydslUsePredicateBoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board> {
}