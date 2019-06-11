package com.example.basic.repository;

import com.example.basic.domain.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BoardRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void whenFindById_thenReturnBoard() {
        // given
        Board testBoard = new Board(1L, "이름", "제목", "내용", LocalDateTime.now());
        testEntityManager.persist(testBoard);
        testEntityManager.flush();

        // when
        Optional<Board> foundBoard = boardRepository.findById(testBoard.getId());

        // then
        assertThat(foundBoard.get().getName()).isEqualTo(testBoard.getName());
    }
}