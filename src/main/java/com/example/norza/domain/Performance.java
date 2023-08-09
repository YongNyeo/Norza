package com.example.norza.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Performance {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
    public String parking; //주차장보유여부
    public String location1; //소재지도로명주소
    public String location2; //소재지지번주소

    public Performance(String name, String location, String content, String startDate, String endDate, String fee, String org, String open_org, String phone_num, String sponsor, String seat, String fee_num, String age, String warning, String parking, String location1, String location2) {
        this.name = name;
        this.location = location;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fee = fee;
        this.org = org;
        this.open_org = open_org;
        this.phone_num = phone_num;
        this.sponsor = sponsor;
        this.seat = seat;
        this.fee_num = fee_num;
        this.age = age;
        this.warning = warning;
        this.parking = parking;
        this.location1 = location1;
        this.location2 = location2;
    }
}
