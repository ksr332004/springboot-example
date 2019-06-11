package com.example.basic.repository;

import com.example.basic.domain.Board;
import com.example.basic.dto.BoardRequest;
import com.example.basic.dto.PageRequest;
import com.example.basic.repository.querydsl.QuerydslCustomizedBoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class QuerydslBoardRepository {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private QuerydslCustomizedBoardRepository querydslCustomizedBoardRepository;

    @Test
    public void whenFindById_thenReturnBoard() {
        // given
        BoardRequest boardRequest = new BoardRequest("이름2", "", "");
        boardRepository.saveAll(Arrays.asList(
                new Board(1L, "이름1", "제목1", "내용1", LocalDateTime.now()),
                new Board(2L, "이름1", "제목2", "내용2", LocalDateTime.now()),
                new Board(3L, "이름2", "제목3", "내용2", LocalDateTime.now())
        ));
        PageRequest pageRequest = new PageRequest();

        // when
        Page<Board> boards = querydslCustomizedBoardRepository.findByCriteria(boardRequest, pageRequest.of());

        // then
        assertThat(boards.getSize()).isEqualTo(1);
        assertThat(boards.getContent().get(0).getName()).isEqualTo("이름1");
    }
}
