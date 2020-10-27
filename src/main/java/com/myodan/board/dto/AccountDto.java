package com.myodan.board.dto;

import com.myodan.board.domain.entity.Account;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String username;
    private String password;
    private LocalDateTime createdDate;

    public Account toEntity() {
        return Account.builder()
                .id(id)
                .username(username)
                .password(password)
                .build();
    }

    @Builder
    public AccountDto(Long id, String username, String password, LocalDateTime createdDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createdDate = createdDate;
    }
}
