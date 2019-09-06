package com.example.board.service;

import com.example.board.dto.BoardDTO;
import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
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
    @Rollback
    public void 게시글_저장_테스트() {
        // Given
        BoardDTO.RequestToCreate boardDTO = new BoardDTO.RequestToCreate();
        boardDTO.setTitle("Title");
        boardDTO.setContent("Content");

        // When
        BoardDTO.ResponseToDetail savedBoard = boardService.insertBoard(boardDTO);

        // Then
        assertThat(boardRepository.findById(savedBoard.getId())).isNotEmpty();
    }

    @Test
    @Rollback
    public void 게시글_수정_테스트() {
        // Given
        String originalTitle = "Original Title";
        String originalContent = "Original Content";
        String changedTitle = "Changed Title";

        Board board = boardRepository.save(
                Board.builder()
                        .title(originalTitle)
                        .content(originalContent)
                        .build());

        // When
        BoardDTO.RequestToUpdate boardDTO = new BoardDTO.RequestToUpdate();
        boardDTO.setId(board.getId());
        boardDTO.setTitle(changedTitle);
        boardDTO.setContent(originalContent);
        BoardDTO.ResponseToDetail updatedBoard = boardService.updateBoard(boardDTO);

        // Then
        assertThat(updatedBoard.getId()).isEqualTo(board.getId());
        assertThat(updatedBoard.getTitle()).isEqualTo(changedTitle);
        assertThat(updatedBoard.getContent()).isEqualTo(originalContent);
    }

}