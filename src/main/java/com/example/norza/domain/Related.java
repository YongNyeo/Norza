package com.example.norza.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Related {
    private String title;
    private String snippet;
    private String link;

    public Related(String title, String snippet, String link) {
        this.title = title;
        this.snippet = snippet;
        this.link = link;
    }
}
