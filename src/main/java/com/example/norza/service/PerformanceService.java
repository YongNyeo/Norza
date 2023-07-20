package com.example.norza.service;

import com.example.norza.domain.Performance;
import com.example.norza.domain.Search;
import com.example.norza.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.io.*;
import java.net.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PerformanceService {
    private final PerformanceRepository performanceRepository;

    public List<Performance> findAll() {
        List<Performance> performanceList = performanceRepository.findAll(Sort.by(Sort.Direction.ASC, "startDate"));
        return performanceList;
    }
    public Page<Performance> page() {
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by("endDate").ascending());
        long totalElements = performanceRepository.findAllByOrderByEndDateAsc(pageRequest).getTotalElements();
        Page<Performance> performanceList = performanceRepository.findAllByOrderByEndDateAsc(pageRequest);

        return performanceList;

    }

    public Performance findById(long id) {
        return performanceRepository.findById(id);
    }

    public Map<String, String> getMapById(long id) {
        Map<String, String> map = new LinkedHashMap<>();
        Performance performance = performanceRepository.findById(id);
        map.put("공연명", performance.getName());
        map.put("개최장소", performance.getLocation());
        map.put("공연 시작일자", performance.getStartDate());
        map.put("공연 종료일자", performance.getEndDate());
        map.put("공연 내용", performance.getContent());
        map.put("요금정보", performance.getFee());
        map.put("관람요금", performance.getFee_num());
        map.put("객석수", performance.getSeat());
        map.put("입장연령", performance.getAge());
        map.put("유의사항", performance.getWarning());
        map.put("주관기관", performance.getOrg());
        map.put("주최기관", performance.getOpen_org());
        map.put("후원기관", performance.getSponsor());

        map.put("관련정보", performance.getEtc());
        map.put("소재지 도로명주소", performance.getLocation1());
        map.put("소재지 지번주소", performance.getLocation2());
        return map;
    }

    //네이버
    public List<Search> jsonToList(String name) throws IOException {
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

    private List<Search> parsing(String sb) {
        JSONParser parser = new JSONParser();
        ArrayList<Search> searchList = new ArrayList<>();
        try {
            JSONObject JsonObj = (JSONObject) parser.parse(sb);
            JSONArray jsonArray = (JSONArray) JsonObj.get("items");
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                if (object.isEmpty()) {
                    break;
                }
                Search search = new Search(((String) object.get("title")).replaceAll("<b>","").replaceAll("</b>",""), ((String) object.get("description")).replaceAll("<b>","").replaceAll("</b>",""), (String) object.get("link"));
                searchList.add(search);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return searchList;
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


