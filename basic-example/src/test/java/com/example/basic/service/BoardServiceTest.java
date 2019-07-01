package com.example.basic.service;

import com.example.basic.domain.Board;
import com.example.basic.repository.BoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BoardService.class)
public class BoardServiceTest {
    @MockBean
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;

    @Test
    public void whenSaveBoard_thenBoardIsCreated() {
        // given
        Board board = Board.builder().name("이름").title("제목").content("내용").build();

        // when
        boardService.saveBoard(board);

        // then
        verify(boardRepository, times(1)).save(board);
    }
}