package com.example.norza.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class FestivalComment extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne()
    @JoinColumn(name="festival_id")
    private Festival festival;

    private String content;
    private String writer;

}
