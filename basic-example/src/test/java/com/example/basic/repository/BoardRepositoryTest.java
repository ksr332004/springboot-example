package com.example.basic.repository;

import com.example.basic.domain.Board;
import com.example.basic.domain.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void whenFindById_thenReturnBoard() {
        // given
        Board board = Board.builder().name("이름").title("제목").content("내용").build();

        boardRepository.save(board);

        // when
        Optional<Board> foundBoard = boardRepository.findById(board.getId());

        // then
        assertThat(foundBoard.get().getName()).isEqualTo(board.getName());
    }

    @Test
    @Transactional
    public void whenFindByBoardId_thenReturnComment() {
        // given
        Board board1 = Board.builder().name("이름").title("제목").content("내용").comments(new ArrayList<>()).build();
        Comment comment1 = Comment.builder().author("아이디1").content("내용1").build();
        Comment comment2 = Comment.builder().author("아이디2").content("내용2").build();

        boardRepository.save(board1);

        board1.addComments(comment1);
        board1.addComments(comment2);

        comment1.setBoard(board1);
        comment2.setBoard(board1);

        commentRepository.save(comment1);
        commentRepository.save(comment2);

        // when
        Board board = boardRepository.findById(board1.getId()).get();

        // then
        assertThat(board.getComments().size()).isEqualTo(2);
    }
}