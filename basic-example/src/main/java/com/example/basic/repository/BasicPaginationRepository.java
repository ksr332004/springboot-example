package com.example.basic.repository;

import com.example.basic.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicPaginationRepository extends JpaRepository<Board, Long> {
    Page<Board> findAll(Pageable pageable);
}
