package com.example.basic.repository.querydsl;

import com.example.basic.domain.Board;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface QuerydslUsePredicateBoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board> {
}