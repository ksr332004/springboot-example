package com.example.board.controller;

import com.example.board.entity.Board;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards")
    public ResponseEntity<List<Board>> getBoardList() {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.selectBoardList());
    }

    @PostMapping("/board")
    public ResponseEntity<String> addBoard(@RequestBody Board board) {
        boardService.insertBoard(board);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @PutMapping("/board")
    public ResponseEntity<Board> updateBoard(@RequestBody Board board) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.updateBoard(board));
    }
}
