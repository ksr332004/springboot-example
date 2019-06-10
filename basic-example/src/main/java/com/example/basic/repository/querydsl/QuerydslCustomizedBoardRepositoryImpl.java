package com.example.basic.repository.querydsl;

import com.example.basic.domain.Board;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import static com.example.basic.domain.QBoard.board;

public class QuerydslCustomizedBoardRepositoryImpl extends QuerydslRepositorySupport implements QuerydslCustomizedBoardRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public QuerydslCustomizedBoardRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Board.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<Board> findByCriteria(String name, String title, String content, Pageable pageable) {
        QueryResults<Board> query = jpaQueryFactory
                .selectFrom(board)
                .where(likeName(name),)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(query.getResults(), pageable, query.getTotal());
    }

    private BooleanExpression likeName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        return board.name.like("%" + name + "%");
    }

    private BooleanExpression likeTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            return null;
        }
        return board.title.like("%" + title + "%");
    }

    private BooleanExpression likeContent(String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        return board.content.like("%" + content + "%");
    }
}