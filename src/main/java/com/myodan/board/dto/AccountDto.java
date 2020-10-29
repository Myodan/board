package com.myodan.board.dto;

import com.myodan.board.domain.entity.Account;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AccountDto {

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$")
    private String username;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{0,100}$")
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9]{0,100}$")
    private String passwordCheck;

    public Account toEntity() {
        return Account.builder()
                .username(username)
                .password(password)
                .build();
    }

    @Builder
    public AccountDto(String username, String password, String passwordCheck) {
        this.username = username;
        this.password = password;
        this.passwordCheck = passwordCheck;
    }
}