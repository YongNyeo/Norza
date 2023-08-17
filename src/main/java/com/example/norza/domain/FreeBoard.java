package com.example.norza.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@NoArgsConstructor
public class FreeBoard extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String title;

    @NotBlank
    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    public FreeBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void saveUser(User user) {
        this.user = user;
        this.name = user.getNickname();
    }
}
