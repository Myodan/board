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

    private Long id;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$")
    private String username;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{0,100}$")
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{1,10}")
    private String nickname;

    @Pattern(regexp = "^[a-zA-Z0-9]{0,100}$")
    private String passwordCheck;

    public Account toEntity() {
        return Account.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .build();
    }

    @Builder
    public AccountDto(Long id, String username, String password, String passwordCheck) {
        this.username = username;
        this.password = password;
        this.passwordCheck = passwordCheck;
    }
}