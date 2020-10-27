package com.myodan.board.service;

import com.myodan.board.util.Hashing;
import com.myodan.board.domain.repository.AccountRepository;
import com.myodan.board.dto.AccountDto;
import com.myodan.board.util.UserInfo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Long signup(AccountDto accountDto) {
        if (accountRepository.findByUsername(accountDto.getUsername()) != null) {
            return -1L;
        }
        accountDto.setPassword(Hashing.hashingPassword(accountDto.getPassword()));
        return accountRepository.save(accountDto.toEntity()).getId();
    }

    @Transactional
    public Long signin(AccountDto accountDto) {
        String username = accountDto.getUsername();
        String password = Hashing.hashingPassword(accountDto.getPassword());
        if (accountRepository.findByUsernameAndPassword(username, password) != null) {
            System.out.println("로그인성공");
        }
        return 0L;
    }
}
