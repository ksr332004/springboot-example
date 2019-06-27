package com.example.basic.repository;

import com.example.basic.domain.Board;
import com.example.basic.dto.BoardRequest;
import com.example.basic.dto.PageRequest;
import com.example.basic.repository.querydsl.QuerydslBasicPaginationBoardRepository;
import com.example.basic.repository.querydsl.QuerydslCustomizedBoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuerydslBoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private QuerydslCustomizedBoardRepository querydslCustomizedBoardRepository;
    @Autowired
    private QuerydslBasicPaginationBoardRepository querydslBasicPaginationBoardRepository;

    @Test
    public void whenFindByCriteria_thenReturnBoard() {
        // given
        BoardRequest boardRequest = new BoardRequest("이름2", "", "");
        boardRepository.saveAll(Arrays.asList(
                Board.builder().name("이름1").title("제목1").content("내용1").build(),
                Board.builder().name("이름1").title("제목2").content("내용2").build(),
                Board.builder().name("이름2").title("제목3").content("내용2").build()
        ));
        PageRequest pageRequest = new PageRequest();

        // when
        Page<Board> boards = querydslCustomizedBoardRepository.findByCriteria(boardRequest, pageRequest.of());

        // then
        assertThat(boards.getTotalElements()).isEqualTo(1L);
        assertThat(boards.getContent().get(0).getName()).isEqualTo("이름2");
    }

    @Test
    public void whenFindAllPagination_thenReturnBoard() {
        // given
        boardRepository.saveAll(Arrays.asList(
                Board.builder().name("이름1").title("제목1").content("내용1").build(),
                Board.builder().name("이름1").title("제목2").content("내용2").build(),
                Board.builder().name("이름2").title("제목3").content("내용2").build()
        ));
        PageRequest pageRequest = new PageRequest();

        // when
        Page<Board> boards = querydslBasicPaginationBoardRepository.findAll(pageRequest.of());

        // then
        assertThat(boards.getTotalElements()).isEqualTo(3);
        assertThat(boards.getTotalPages()).isEqualTo(1);
    }
}
