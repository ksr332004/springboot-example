package com.example.basic.repository.querydsl;

import com.example.basic.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuerydslCustomizedBoardRepository extends JpaRepository<Board, Long>, QuerydslCustomizedBoardRepositoryCustom {
}