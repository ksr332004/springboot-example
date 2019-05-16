package com.example.basic.service;

import com.example.basic.domain.Board;
import com.example.basic.repository.BoardRepository;
import com.example.basic.service.impl.BoardServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BoardServiceImpl.class)
public class BoardServiceImplTest {
    @MockBean
    private BoardRepository boardRepository;
    @Autowired
    private BoardServiceImpl BoardServiceImpl;

    @Test
    public void whenSaveBoard_thenReturnBoard() {
        // given
        Board testBoard = new Board(1L, "이름", "제목", "내용", LocalDateTime.now());
        when(boardRepository.save(any(Board.class))).thenReturn(new Board());

        // when
        Board saveBoard = BoardServiceImpl.saveBoard(testBoard);

        // then
        assertThat(saveBoard.getId()).isEqualTo(testBoard.getId());
    }
}