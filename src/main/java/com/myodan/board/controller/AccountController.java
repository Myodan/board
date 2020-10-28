package com.myodan.board.controller;

import com.myodan.board.dto.AccountDto;
import com.myodan.board.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account/signup")
    public String getSignUp() {
        return "account/signup.html";
    }

    @PostMapping("/account/signup")
    public String postSignUp(@Valid AccountDto accountDto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("accountDto", accountDto);
            Map<String, String> validatorResult = accountService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
        }
        accountService.signUp(accountDto);
        return "account/signup.html";
    }

    @GetMapping("/account/signin")
    public String getSignIn() {
        return "account/signin.html";
    }

    @PostMapping("/signin")
    public String postSignIn() {
        return "redirect:/";
    }

    @GetMapping("/account/signout")
    public String signOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}