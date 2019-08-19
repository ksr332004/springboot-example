package com.example.board.controller;

import com.example.board.entity.Board;
import com.example.board.entity.Comment;
import com.example.board.service.BoardService;
import com.example.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final BoardService boardService;
    private final CommentService commentService;

    @PostMapping("/board/{id}/comment")
    public ResponseEntity<Board> addComment(@PathVariable Long id, @RequestBody Comment comment) {
        commentService.insertComment(id, comment);
        return ResponseEntity.status(HttpStatus.OK).body(boardService.selectBoard(id));
    }

    @PutMapping("/board/{id}/comment")
    public ResponseEntity<Board> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        commentService.updateComment(comment);
        return ResponseEntity.status(HttpStatus.OK).body(boardService.selectBoard(id));
    }

    @DeleteMapping("/board/{boardId}/comment/{commentId}")
    public ResponseEntity<Board> deleteComment(@PathVariable Long boardId, @PathVariable Long commentId) {
        commentService.deleteComment(boardId, commentId);
        return ResponseEntity.status(HttpStatus.OK).body(boardService.selectBoard(boardId));
    }

}
