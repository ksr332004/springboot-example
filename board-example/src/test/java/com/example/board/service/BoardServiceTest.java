package com.example.board.service;

import com.example.board.dto.BoardDTO;
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
    public void 게시글_수정_테스트() {
        String beforeTitle = "제목1";
        String beforeContent = "내용1";
        String afterTitle = "제목2";

        // given
        Board board = boardRepository.save(Board.builder().title(beforeTitle).content(beforeContent).build());

        // when
        BoardDTO.RequestToUpdate boardDTO = new BoardDTO.RequestToUpdate();
        boardDTO.setId(board.getId());
        boardDTO.setTitle(afterTitle);
        boardDTO.setContent(beforeContent);
        Board updatedBoard = boardService.updateBoard(boardDTO);

        // then
        assertThat(updatedBoard.getTitle()).isEqualTo(afterTitle);
    }

}