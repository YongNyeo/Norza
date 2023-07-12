package com.example.norza.domain;

import lombok.Getter;

import java.io.Serializable;


//세션에 유저의 모든 정보가 드러나면 안됨. 꼭 필요한것만 넘겨줘야함
@Getter
public class SessionUser implements Serializable {
    private String email;
    private String nickName;
    public SessionUser(User user) {
        this.email = user.getEmail();
        this.nickName = user.getNickname();
    }
}
