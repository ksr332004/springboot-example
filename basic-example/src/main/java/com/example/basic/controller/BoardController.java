package com.example.basic.controller;

import com.example.basic.domain.Board;
import com.example.basic.dto.PageRequest;
import com.example.basic.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/write")
    public String showWriteBoardForm(Board board) {
        return "write-board";
    }

    @PostMapping("/write/add")
    public String saveBoard(@Valid Board board, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "write-board";
        }
        boardService.saveBoard(board);
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String showListBoardForm(Model model) {
        model.addAttribute("getUri", "none");
        model.addAttribute("boards", boardService.findAll());
        return "list-board";
    }

    @GetMapping("/list/jpa1")
    public String showBasic1ListBoardForm(Pageable pageable, Model model) {
        model.addAttribute("getUri", "jpa1");
        model.addAttribute("boards", boardService.basicPaginationFindAll(pageable));
        return "list-board";
    }

    @GetMapping("/list/jpa2")
    public String showBasic2ListBoardForm(PageRequest pageRequest, Model model) {
        model.addAttribute("getUri", "jpa2");
        model.addAttribute("boards", boardService.basicPaginationFindAll(pageRequest.of()));
        return "list-board";
    }

    @GetMapping("/list/querydsl")
    public String showQuerydalListBoardForm(PageRequest pageRequest, Model model) {
        model.addAttribute("getUri", "querydsl");
        model.addAttribute("boards", boardService.querydslPaginationFindAll(pageRequest.of()));
        return "list-board";
    }

    @GetMapping("/edit/{id}")
    public String showEditBoardForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("board", boardService.findBoardById(id));
        return "edit-board";
    }

    @PostMapping("/edit/update")
    public String updateBoard(@Valid Board board, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("board", boardService.findBoardById(board.getId()));
            return "edit-board";
        }
        boardService.updateBoard(board);
        return "redirect:/board/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteBoardById(@PathVariable("id") Long id) {
        boardService.deleteBoardById(id);
        return "redirect:/board/list";
    }
}
