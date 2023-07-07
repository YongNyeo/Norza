package com.example.norza.init;


import com.example.norza.domain.Performance;
import com.example.norza.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Configuration;


import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Configuration
public class InitPerformance {
    private final PerformanceRepository performanceRepository;

    @PostConstruct
    public void init() {
        try {
            JSONParser jsonParser = new JSONParser();
            Reader reader = new FileReader("/Users/mac/Downloads/전국공연행사정보표준데이터.json");
            JSONObject data = (JSONObject) jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) data.get("records");
            int cnt = 0;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate now = LocalDate.now();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                String s = (String)object.get("행사종료일자");
                LocalDate dateTime = LocalDate.parse(s, formatter);

                if (dateTime.isBefore(now)) { //이미 늦은 행사는 X
                    continue;
                }
                String name = (String) object.get("행사명");
                String location= (String) object.get("장소");
                String content = (String) object.get("행사내용");
                String startDate= (String) object.get("행사시작일자");
                String endDate = (String) object.get("행사종료일자");
                String fee = (String) object.get("요금정보");
                String org = (String) object.get("주관기관");
                String open_org= (String) object.get("주최기관");
                String phone_num=  (String) object.get("전화번호");
                String sponsor= (String) object.get("후원기관");
                String seat= (String) object.get("객석수");
                String fee_num =  (String) object.get("관람요금");
                String age=  (String) object.get("입장연령");//입장연령
                String etc= (String) object.get("할인정보");
                String warning= (String) object.get("유의사항");
                String homepage= (String) object.get("홈페이지");
                String rsv_info= (String) object.get("예약정보");
                String parking= (String) object.get("주차장보유여부");
                String location1= (String) object.get("소재지도로명주소");
                String location2= (String) object.get("소재지지번주소");
                cnt++;
                performanceRepository.save(new Performance(cnt, name, location, content, startDate, endDate, fee, org, open_org, phone_num, sponsor, seat, fee_num, age, etc, warning, homepage, rsv_info, parking, location1, location2));

            }
            System.out.println(cnt);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
