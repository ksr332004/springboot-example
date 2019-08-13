package com.example.board.service;

import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardServiceTest {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    BoardService boardService;

    @Test
    public void 게시글_수정_테스트() throws Exception {
        // given
        Board board = boardRepository.save(Board.builder().title("제목1").content("내용1").build());

        // when
        board.setTitle("제목2");
        Board updatedBoard = boardService.updateBoard(board);

        // then
        assertThat(updatedBoard.getTitle()).isEqualTo("제목2");
    }

}