package com.myodan.board.controller;

import com.myodan.board.dto.BoardDto;
import com.myodan.board.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public String boradPage(@PageableDefault(sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        Page<BoardDto> postList = boardService.getPostList(pageable);
        model.addAttribute("postList", postList);
        return "board/list";
    }

    @GetMapping("/post")
    public String post(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.invalidate();
            return "redirect:/";
        }
        return "board/post.html";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto, HttpServletRequest request) {
        String username = request.getSession().getAttribute("username").toString();
        boardDto.setAuthor(username);
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "board/detail.html";
    }

    @GetMapping("/post/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "board/edit.html";
    }

    @PutMapping("/post/edit/{id}")
    public String update(HttpServletRequest request, BoardDto boardDto) {
        String username = request.getSession().getAttribute("username").toString();
        boardDto.setAuthor(username);
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    @DeleteMapping("/post/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.deletePost(id);
        return "redirect:/";
    }
}