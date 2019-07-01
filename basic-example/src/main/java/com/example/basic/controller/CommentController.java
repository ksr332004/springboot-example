package com.example.basic.controller;

import com.example.basic.domain.Comment;
import com.example.basic.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/write/add")
    public String saveComment(@Valid Comment comment, BindingResult bindingResult) {
        Long boardId = comment.getBoard().getId();

        if (bindingResult.hasErrors()) {
            return "redirect:/board/view/" + boardId;
        }

        commentService.saveComment(boardId, comment);
        return "redirect:/board/view/" + boardId;
    }

    @GetMapping("/delete/{id}")
    public String deleteComment(@PathVariable("id") Long id) {
        Optional<Comment> comment = commentService.findCommentById(id);
        if ( !comment.isPresent() ) {
            return "redirect:/";
        }
        commentService.deleteCommentByBoardIdAndCommentId(comment.get().getBoard().getId(), id);
        return "redirect:/board/view/" + comment.get().getBoard().getId();
    }
}