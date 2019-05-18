package com.example.basic.repository;

import com.example.basic.domain.Board;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.example.basic.domain.QBoard.board;

@Repository
public class QuerydslPaginationRepository extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public QuerydslPaginationRepository(JPAQueryFactory jpaQueryFactory) {
        super(Board.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Page<Board> findAll(Pageable pageable) {
        QueryResults<Board> results = jpaQueryFactory
                .selectFrom(board)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
}
