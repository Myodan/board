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

    @NotNull(message = "계정이름은 필수 입력값입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$",
            message = "계정이름은 영문 대, 소문자와 숫자가 포함된 5~20자의 계정이름이여야 합니다.")
    private String username;

    @NotNull(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,30}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8~30자의 비밀번호여야 합니다.")
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
