package com.myodan.board.service;

import com.myodan.board.domain.repository.AccountRepository;
import com.myodan.board.dto.AccountDto;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public List<String> signUp(AccountDto accountDto) {
        List<String> errors = new ArrayList<>();
        if (accountRepository.findByUsername(accountDto.getUsername()) != null) {
            errors.add("이미 존재하는 사용자 이름입니다!");
        }
        if (!accountDto.getPassword().equals(accountDto.getPasswordCheck())) {
            System.out.println(accountDto.getPassword());
            System.out.println(accountDto.getPasswordCheck());
            errors.add("비밀번호가 일치하지 않습니다!");
        }

        if (errors.isEmpty()) {
            accountRepository.save(accountDto.toEntity());
        }

        return errors;
    }

    @Transactional
    public List<String> signIn(AccountDto accountDto, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();
        String username = accountDto.getUsername();
        String password = accountDto.getPassword();

        if (accountRepository.findByUsernameAndPassword(username, password) == null) {
            errors.add("알 수 없는 사용자입니다. 사용자 이름과 암호를 다시 확인해주세요!");
        }

        if (errors.isEmpty()) {
            HttpSession session = request.getSession();
            if(session.isNew()){
                session.setAttribute("signin", true);
                session.setAttribute("username", accountDto.getUsername());
            }
        }

        return errors;
    }
}
