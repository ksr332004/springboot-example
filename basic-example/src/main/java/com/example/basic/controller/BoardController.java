package com.example.basic.controller;

import com.example.basic.domain.Board;
import com.example.basic.domain.Comment;
import com.example.basic.dto.BoardRequest;
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
import java.util.Optional;

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
        model.addAttribute("boards", boardService.findAll());
        return "basic-list-board";
    }

    @GetMapping("/list/jpa1")
    public String showBasic1ListBoardForm(Pageable pageable, Model model) {
        model.addAttribute("getMainUri", "jpa1");
        model.addAttribute("boards", boardService.jpaBasicPaginationFindAll(pageable));
        return "pagination-list-board";
    }

    @GetMapping("/list/jpa2")
    public String showBasic2ListBoardForm(PageRequest pageRequest, Model model) {
        model.addAttribute("getMainUri", "jpa2");
        model.addAttribute("boards", boardService.jpaBasicPaginationFindAll(pageRequest.of()));
        return "pagination-list-board";
    }

    @GetMapping("/list/querydsl")
    public String showQuerydslListBoardForm(PageRequest pageRequest, Model model) {
        model.addAttribute("getMainUri", "querydsl");
        model.addAttribute("boards", boardService.querydslBasicPaginationFindAll(pageRequest.of()));
        return "pagination-list-board";
    }

    @GetMapping("/list/jpa/search")
    public String showjpaSearchListBoardForm(BoardRequest boardRequest, PageRequest pageRequest, Model model) {
        model.addAttribute("getParamsUri", boardRequest.getUri());
        model.addAttribute("boards", boardService.jpaUsePredicateFindByCriteria(boardRequest, pageRequest.of()));
        return "search-list-board";
    }

    @GetMapping("/list/querydsl/search1")
    public String showQuerydslSearch1ListBoardForm(BoardRequest boardRequest, PageRequest pageRequest, Model model) {
        model.addAttribute("getParamsUri", boardRequest.getUri());
        model.addAttribute("boards", boardService.qureydslUsePredicateFindByCriteria(boardRequest, pageRequest.of()));
        return "search-list-board";
    }

    @GetMapping("/list/querydsl/search2")
    public String showQuerydslSearch2ListBoardForm(BoardRequest boardRequest, PageRequest pageRequest, Model model) {
        model.addAttribute("getParamsUri", boardRequest.getUri());
        model.addAttribute("boards", boardService.querydslCustomizedFindByCriteria(boardRequest, pageRequest.of()));
        return "search-list-board";
    }

    @GetMapping("/view/{id}")
    public String showViewBoardForm(@PathVariable("id") Long id, Comment comment, Model model) {
        Optional<Board> board = boardService.findBoardById(id);
        model.addAttribute("board", board);
        board.map( b -> model.addAttribute("comments", b.getComments().size() > 0 ? b.getComments() : null));
        return "view-board";
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
    public String deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoardById(id);
        return "redirect:/board/list";
    }
}
