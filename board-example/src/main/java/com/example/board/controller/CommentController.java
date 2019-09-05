package com.example.board.controller;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.CommentDTO;
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
    public ResponseEntity<BoardDTO.ResponseToDetail> addComment(@PathVariable Long id, @RequestBody CommentDTO.RequestToCreate commentDTO) {
        commentService.insertComment(id, commentDTO);
        return ResponseEntity.status(HttpStatus.OK).body(boardService.selectBoard(id));
    }

    @PutMapping("/board/{id}/comment")
    public ResponseEntity<BoardDTO.ResponseToDetail> updateComment(@PathVariable Long id, @RequestBody CommentDTO.RequestToUpdate commentDTO) {
        commentService.updateComment(commentDTO);
        return ResponseEntity.status(HttpStatus.OK).body(boardService.selectBoard(id));
    }

    @DeleteMapping("/board/{boardId}/comment/{commentId}")
    public ResponseEntity<BoardDTO.ResponseToDetail> deleteComment(@PathVariable Long boardId, @PathVariable Long commentId) {
        commentService.deleteComment(boardId, commentId);
        return ResponseEntity.status(HttpStatus.OK).body(boardService.selectBoard(boardId));
    }

}
