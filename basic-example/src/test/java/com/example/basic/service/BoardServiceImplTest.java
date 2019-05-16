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

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BoardServiceImpl.class)
public class BoardServiceImplTest {
    @MockBean
    private BoardRepository boardRepository;
    @Autowired
    private BoardServiceImpl BoardServiceImpl;

    @Test
    public void whenSaveBoard_thenBoardIsCreated() {
        // given
        Board testBoard = new Board(1L, "이름", "제목", "내용", LocalDateTime.now());

        // when
        BoardServiceImpl.saveBoard(testBoard);

        // then
        verify(boardRepository, times(1)).save(testBoard);
    }
}