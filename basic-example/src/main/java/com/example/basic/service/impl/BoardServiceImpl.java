package com.example.basic.service.impl;

import com.example.basic.domain.Board;
import com.example.basic.repository.BoardRepository;
import com.example.basic.service.BoardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public Optional<Board> findBoardById(Long id) {
        return boardRepository.findById(id);
    }

    @Override
    public Optional<Board> findBoardByTitle(String title) {
        return boardRepository.findByTitle(title);
    }

    @Override
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Override
    public Board updateBoard(Board board) {
        Optional<Board> updateBoard = boardRepository.findById(board.getId());
        if (updateBoard.isPresent()) {
            updateBoard.get().setName(board.getName());
            updateBoard.get().setTitle(board.getTitle());
            updateBoard.get().setContent(board.getContent());
            boardRepository.save(updateBoard.get());
        }
        return null;
    }

    @Override
    public void deleteBoardById(Long id) {
        Optional<Board> deleteBoard = boardRepository.findById(id);
        if (deleteBoard.isPresent()) {
            boardRepository.delete(deleteBoard.get());
        }
        return ;
    }
}
