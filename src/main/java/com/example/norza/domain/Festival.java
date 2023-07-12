package com.example.norza.domain;


import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Festival extends BaseTime{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String name; //축제명
    private String location; //개최장소
    private String startDate ;//축제시작일자
    private String endDate ; //축제종료일자
    private String content ; //축제내용
    private String org ; //주관기관
    private String open_org; //주최기관
    private String sponsor; //후원기관
    private String phone_num; //전화번호
    private String homepage; //홈페이지주소
    private String etc; //관련정보
    private String location1; //소재지도로명주소
    private String location2; //소재지지번주소

    public Festival(String name, String location, String startDate, String endDate, String content, String org, String open_org, String sponsor, String phone_num, String homepage, String etc, String location1, String location2) {
        this.name = name;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.content = content;
        this.org = org;
        this.open_org = open_org;
        this.sponsor = sponsor;
        this.phone_num = phone_num;
        this.homepage = homepage;
        this.etc = etc;
        this.location1 = location1;
        this.location2 = location2;
    }
}


