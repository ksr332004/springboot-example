package com.example.basic.controller;

import com.example.basic.domain.Board;
import com.example.basic.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/write")
    public String showWriteBoardForm(Board board) {
        return "write-board";
    }

    @PostMapping("/write/add")
    public String saveBoard(@Valid Board board, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "write-board";
        }
        boardService.saveBoard(board);
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String showListBoardForm(Model model) {
        model.addAttribute("boards", boardService.findAll());
        return "list-board";
    }

    @GetMapping("/edit/{id}")
    public String showEditBoardForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("board", boardService.findBoardById(id));
        return "edit-board";
    }

    @PostMapping("/edit/update")
//    @PutMapping("/edit/update")
    public String updateBoard(@Valid Board board, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("board", boardService.findBoardById(board.getId()));
            return "edit-board";
        }
        boardService.updateBoard(board);
        return "redirect:/board/list";
    }

    @GetMapping("/delete/{id}")
//    @DeleteMapping("/delete/{id}")
    public String deleteBoardById(@PathVariable("id") Long id) {
        boardService.deleteBoardById(id);
        return "redirect:/board/list";
    }
}
