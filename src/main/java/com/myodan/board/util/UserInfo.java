package com.myodan.board.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

@Getter
@Setter
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@ToString
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    @Builder
    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }
}