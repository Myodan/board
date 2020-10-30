package com.myodan.board.controller;

import com.myodan.board.dto.AccountDto;
import com.myodan.board.dto.BoardDto;
import com.myodan.board.service.AccountService;
import com.myodan.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class AccountController {

    private final AccountService accountService;
    private final BoardService boardService;

    public AccountController(AccountService accountService, BoardService boardService) {
        this.accountService = accountService;
        this.boardService = boardService;
    }

    @GetMapping("/account/signup")
    public String getSignUp() {
        return "/account/signup";
    }

    @PostMapping("/account/signup")
    public String postSignUp(@Valid AccountDto accountDto, Model model) {
        List<String> errors = accountService.signUp(accountDto);
        if (errors.isEmpty()) {
            return "/account/result";
        } else {
            model.addAttribute("errors", errors);
            return "/account/signup";
        }
    }

    @GetMapping("/account/signin")
    public String getSignIn() {
        return "/account/signin";
    }

    @PostMapping("/account/signin")
    public String postSignIn(@Valid AccountDto accountDto, Model model, HttpServletRequest request) {
        List<String> errors = accountService.signIn(accountDto, request);
        if (errors.isEmpty()) {
            return "redirect:/";
        } else {
            model.addAttribute("errors", errors);
            return "/account/signin";
        }
    }

    @GetMapping("/account/signout")
    public String getSignOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

    @GetMapping("/myaccount")
    public String getMyAccount(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.invalidate();
            return "redirect:/account/signin";
        } else {
            return "/myaccount/index";
        }
    }

    @GetMapping("/myaccount/postlist")
    public String getMyAccountBoardList(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.invalidate();
            return "redirect:/account/signin";
        } else {
            String username = session.getAttribute("username").toString();
            List<BoardDto> boardDtoList = boardService.getBoardListByAuthor(username);
            model.addAttribute("postList", boardDtoList);
            return "/myaccount/postlist";
        }
    }
}