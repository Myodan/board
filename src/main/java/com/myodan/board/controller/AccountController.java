package com.myodan.board.controller;

import com.myodan.board.dto.AccountDto;
import com.myodan.board.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/signup")
    public String signUpPost(AccountDto accountDto) {
        accountService.signup(accountDto);
        return "redirect:/";
    }

    @PostMapping("/signin")
    public String signinPost(AccountDto accountDto){

        return "redirect:/";
    }
}