package com.example.basic.repository.querydsl;

import com.example.basic.dto.BoardRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.util.StringUtils;

import static com.example.basic.domain.QBoard.board;

public class QuerydslUsePredicateBoardPredicate {
    public static Predicate search(BoardRequest boardRequest) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (!StringUtils.isEmpty(boardRequest.getName())) {
            booleanBuilder.and(board.name.like("%" + boardRequest.getName() + "%"));
        }
        if (!StringUtils.isEmpty(boardRequest.getTitle())) {
            booleanBuilder.and(board.title.like("%" + boardRequest.getTitle() + "%"));
        }
        if (!StringUtils.isEmpty(boardRequest.getContent())) {
            booleanBuilder.and(board.content.like("%" + boardRequest.getContent() + "%"));
        }

        return booleanBuilder;
    }
}