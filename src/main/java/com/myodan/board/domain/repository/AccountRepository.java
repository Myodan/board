package com.myodan.board.domain.repository;

import com.myodan.board.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findByUsername(String username);
    public Account findByUsernameAndPassword(String username, String password);
    public List<Account> findAll();
}
