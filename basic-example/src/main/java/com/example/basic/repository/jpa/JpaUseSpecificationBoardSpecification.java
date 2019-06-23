package com.example.basic.repository.jpa;

import com.example.basic.domain.Board;
import com.example.basic.dto.BoardRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class JpaUseSpecificationBoardSpecification {
    public static Specification<Board> search(BoardRequest boardRequest) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(boardRequest.getName())) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("name"), "%" + boardRequest.getName() + "%")));
            }
            if (!StringUtils.isEmpty(boardRequest.getTitle())) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("title"), "%" + boardRequest.getTitle() + "%")));
            }
            if (!StringUtils.isEmpty(boardRequest.getContent())) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("content"), "%" + boardRequest.getContent() + "%")));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
