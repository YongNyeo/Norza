package com.example.norza.service;

import com.example.norza.domain.News;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {

    //문화,생활 뉴스만 뽑아오기
    public List<News> CrawlingNews() throws IOException {
        News news = new News();

        return news.Crawling();
    }
}

