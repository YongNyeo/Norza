package com.example.norza.init;

import com.example.norza.domain.Festival;
import com.example.norza.exception.ParsingException;
import com.example.norza.exception.RuntimeFileNotFoundException;
import com.example.norza.repository.FestivalRepository;
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
public class InitFestival {
    private final FestivalRepository festivalRepository ; //빈에 등록되있어서 자동주입됨

    @PostConstruct
    public void init() {
        try {
            if (festivalRepository.findById(1)!=null) {
                return;
            }
            JSONParser jsonParser = new JSONParser();
            Reader reader = new FileReader("/Users/mac/Downloads/전국문화축제표준데이터.json");
            JSONObject data = (JSONObject) jsonParser.parse(reader);
            JSONArray jsonArray =(JSONArray) data.get("records");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate now = LocalDate.now();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                String s = (String)object.get("축제종료일자");
                LocalDate dateTime = LocalDate.parse(s, formatter);

                if (dateTime.isBefore(now)) { //이미 종료한 축제는 X
                    continue;
                }
                String name = (String) object.get("축제명");
                String location = (String) object.get("개최장소");
                String startDate = (String) object.get("축제시작일자");
                String endDate = (String) object.get("축제종료일자");
                String content = (String) object.get("축제내용");
                String org = (String) object.get("주관기관");
                String open_org = (String) object.get("주최기관");
                String sponsor = (String) object.get("후원기관");
                String phone_num = (String) object.get("전화번호");
                String homepage = (String) object.get("홈페이지주소");
                String etc = (String) object.get("관련정보");
                String location1 = (String) object.get("소재지도로명주소");
                String location2 = (String) object.get("소재지지번주소");

                festivalRepository.save(new Festival(name,location,startDate,endDate,content,org,open_org,sponsor,phone_num,homepage,etc,location1,location2));

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeFileNotFoundException(e);
        } catch (IOException | ParseException e) {
            throw new ParsingException(e);
        }
    }
}
