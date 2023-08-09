package com.example.norza.service;

import com.example.norza.domain.Festival;
import com.example.norza.domain.Related;
import com.example.norza.repository.FestivalRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;


@Service
@RequiredArgsConstructor
@Configuration
public class FestivalService {
    private final FestivalRepository festivalRepository;

    public Page<Festival> page(Pageable pageable) {

        Page<Festival> festivalList = festivalRepository.findAll(pageable);
        return festivalList;
    }

    public Festival findById(long id) {
        return festivalRepository.findById(id);
    }

    public Map<String,String> getMapById(long id) {
        Map<String,String>map = new LinkedHashMap<>();
        Festival festival = festivalRepository.findById(id);
        map.put("축제명", festival.getName());
        map.put("개최장소", festival.getLocation());
        map.put("축제 시작일자", festival.getStartDate());
        map.put("축제 종료일자", festival.getContent());
        map.put("주관기관", festival.getOrg());
        map.put("주최기관", festival.getOpen_org());
        map.put("후원기관", festival.getSponsor());
        map.put("관련정보", festival.getEtc());
        map.put("소재지 도로명주소", festival.getLocation1());
        map.put("소재지 지번주소", festival.getLocation2());
        return map;
    }

    public List<Festival> searchList(String selection,String content) {

        if (selection.equals("title")) {
            return festivalRepository.findByNameContaining(content);
        } else if (selection.equals("content")) {
            return festivalRepository.findByContentContaining(content);
        } else return festivalRepository.findByLocationContaining(content);
    }


    //네이버
    public List<Related> jsonToList(String name) throws IOException {
        String clientId = "froS04i71O5XiFeOQm7C"; //애플리케이션 클라이언트 아이디
        String clientSecret = "VTSYLGUPeV"; //애플리케이션 클라이언트 시크릿    }
        String text = null;
        try {
            text = URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패", e);
        }
        String apiURL = "https://openapi.naver.com/v1/search/blog?display=50&query=" + text;
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL, requestHeaders);
        return parsing(responseBody);

    }

    private List<Related> parsing(String sb) {
        JSONParser parser = new JSONParser();
        ArrayList<Related> relatedList = new ArrayList<>();
        try {
            JSONObject JsonObj = (JSONObject) parser.parse(sb);
            JSONArray jsonArray = (JSONArray) JsonObj.get("items");
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                if (object.isEmpty()) {
                    break;
                }
                Related related = new Related(((String) object.get("title")).replaceAll("<b>","").replaceAll("</b>",""), ((String) object.get("description")).replaceAll("<b>","").replaceAll("</b>",""), (String) object.get("link"));
                relatedList.add(related);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return relatedList;
    }

    private static String get(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
}

