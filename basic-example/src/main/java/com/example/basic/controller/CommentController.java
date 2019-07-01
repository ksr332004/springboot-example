package com.example.basic.controller;

import com.example.basic.domain.Comment;
import com.example.basic.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/write/add")
    public String saveBoard(@Valid Comment comment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "view-board";
        }
        commentService.saveComment(comment.getBoard().getId(), comment);
        return "redirect:/board/list";
    }
}