package com.example.norza.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor
public class User extends BaseTime{

    @Id @GeneratedValue
    private Long id;
    private String email;
    private String nickname;

    @Builder
    public User( String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}
