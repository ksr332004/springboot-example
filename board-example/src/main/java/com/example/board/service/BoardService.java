package com.example.board.service;

import com.example.board.entity.Board;
import com.example.board.exception.BusinessException;
import com.example.board.exception.ErrorCode;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> selectBoardList() {
        return boardRepository.findAll();
    }

    public Board selectBoard(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.BOARD_NOT_EXIST));
    }

    public Board insertBoard(Board board) {
        return boardRepository.save(board);
    }

    @Transactional
    public Board updateBoard(Board board) {
        return boardRepository.findById(board.getId())
                .map(b -> {
                    b.setTitle(board.getTitle());
                    b.setContent(board.getContent());
                    return boardRepository.save(b);
                })
                .orElseThrow(() -> new BusinessException(ErrorCode.BOARD_NOT_EXIST));
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

}
