package com.example.norza.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Performance {

    @Id
    private long id;

    public String name; //행사명
    public String location; //장소
    public String content ; //행사내용
    public String startDate ;//행사시작일자
    public String endDate ; //행사종료일자
    public String fee ; //요금정보
    public String org ; //주관기관
    public String open_org; //주최기관
    public String phone_num; //전화번호
    public String sponsor; //후원기관
    public String seat;//객석수
    public String fee_num; //관람요금
    public String age;//입장연령
    public String etc; //할인정보
    public String warning; //유의사항
    public String homepage; //홈페이지주소
    public String rsv_info; //예매정보
    public String parking; //주차장보유여부
    public String location1; //소재지도로명주소
    public String location2; //소재지지번주소

}
