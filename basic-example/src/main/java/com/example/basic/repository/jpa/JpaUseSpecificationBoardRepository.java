package com.example.basic.repository.jpa;

import com.example.basic.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaUseSpecificationBoardRepository extends JpaRepository<Board, Long>, JpaSpecificationExecutor<Board> {
}
