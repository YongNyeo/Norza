package com.example.norza.domain;


import com.example.norza.dto.FreeBoardSaveDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

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



    public FreeBoard(String content) {
        this.content = content;
    }

    public void DtoToFreeBoard(FreeBoardSaveDto dto){
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }

    public void saveUser(User user) {
        this.user = user;
        this.name = user.getNickname();
    }


}


