package com.example.norza.domain;

import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Getter
public class News {
    private String link;
    private String imgLink;
    private String title;
    private String content;

    public List<News> Crawling() throws IOException {
        String url = "https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid1=103&sid2=237";
        Document doc = Jsoup.connect(url).get();

        Elements elementsByClass = doc.getElementsByClass("photo");
        Elements content = doc.getElementsByClass("lede");
        List<News> newsList = new LinkedList<>();
        for (int i=0;i<10;i++) {
            News news = new News();
            newsList.add(news.changeNews(elementsByClass.get(i), content.get(i)));
        }
        return newsList;
    }
    public News changeNews(Element element,Element text) {
        this.link = (element.select("a").attr("abs:href"));
        this.imgLink=(element.select("img").attr("abs:src"));
        this.title = (element.select("img").attr("abs:alt").substring(28));
        this.content =text.text();
        return this;
    }
}

