package com.example.norza.controller;

import com.example.norza.config.Login;
import com.example.norza.domain.SessionUser;
import com.example.norza.service.FestivalService;
import com.example.norza.service.NewsService;
import com.example.norza.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final NewsService newsService;
    private final PerformanceService performanceService;
    private final FestivalService festivalService;

    @GetMapping("/")
    public String home(Model model) throws IOException {
        model.addAttribute("newsList", newsService.CrawlingNews());
        return "home.html";
    }

    //저장하는 기능이 아니라서 PRG 패턴 필요 X. 그냥 바로 보여줘도됨
    @PostMapping("/search")
    public String search(@Valid @RequestParam String selection, @Valid @RequestParam String content, Model model) {
        model.addAttribute("festivalList", festivalService.searchList(selection, content));
        model.addAttribute("performanceList", performanceService.searchList(selection, content));
        return "search.html";

    }

    @GetMapping("/mypage")
    public String myPage(@Login SessionUser user, Model model){
        model.addAttribute("user",user);
        return "mypage.html";
    }
}


