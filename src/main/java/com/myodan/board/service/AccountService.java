package com.myodan.board.service;

import com.myodan.board.util.Hashing;
import com.myodan.board.domain.repository.AccountRepository;
import com.myodan.board.dto.AccountDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Long signUp(AccountDto accountDto) {
        if (accountRepository.findByUsername(accountDto.getUsername()) != null) {
            return -1L;
        }
        accountDto.setPassword(Hashing.hashingPassword(accountDto.getPassword()));
        return accountRepository.save(accountDto.toEntity()).getId();
    }

    @Transactional
    public Long signIn(AccountDto accountDto, HttpServletRequest request) {
        String username = accountDto.getUsername();
        String password = Hashing.hashingPassword(accountDto.getPassword());
        HttpSession session = request.getSession();

        if (accountRepository.findByUsernameAndPassword(username, password) == null)
            return null;

        if(!session.isNew()){
            session.invalidate();
        }

        if (session.isNew()) {
            session.setAttribute("signin", true);
            session.setAttribute("username", username);
        }

        return accountDto.getId();
    }

    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }

    public void signUp() {

    }
}
