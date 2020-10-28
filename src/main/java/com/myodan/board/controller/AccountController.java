package com.myodan.board.controller;

import com.myodan.board.dto.AccountDto;
import com.myodan.board.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/signup")
    public String signUp() {
        return "account/signup.html";
    }

    @GetMapping("/signin")
    public String signIn() {
        return "account/signin.html";
    }

    @GetMapping("/signout")
    public String signOut(HttpServletRequest request) {
        accountService.signOut(request);
        return "redirect:/";
    }

    @PostMapping("/signup")
    public String signUpPost(AccountDto accountDto) {
        accountService.signUp(accountDto);
        return "redirect:/";
    }

    @PostMapping("/signin")
    public String signinPost(AccountDto accountDto, HttpServletRequest request) {
        accountService.signIn(accountDto, request);
        return "redirect:/";
    }
}