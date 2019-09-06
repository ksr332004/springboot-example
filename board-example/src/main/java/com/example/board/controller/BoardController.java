package com.example.board.controller;

import com.example.board.dto.BoardDTO;
import com.example.board.entity.Board;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards")
    public ResponseEntity<List<BoardDTO.ResponseToList>> getBoardList() {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.selectBoardList());
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<BoardDTO.ResponseToDetail> getBoard(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.selectBoard(id));
    }

    @PostMapping("/board")
    public ResponseEntity<BoardDTO.ResponseToDetail> addBoard(@Valid @RequestBody BoardDTO.RequestToCreate boardDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.insertBoard(boardDTO));
    }

    @PutMapping("/board")
    public ResponseEntity<BoardDTO.ResponseToDetail> updateBoard(@RequestBody BoardDTO.RequestToUpdate boardDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.updateBoard(boardDTO));
    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity<List<BoardDTO.ResponseToList>> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.status(HttpStatus.OK).body(boardService.selectBoardList());
    }

}
